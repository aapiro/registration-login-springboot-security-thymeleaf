package com.ilimitech.webapp.realstate.controller;

import com.ilimitech.webapp.realstate.dto.ImageDto;
import com.ilimitech.webapp.realstate.dto.ImageInfoDto;
import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.mapper.PropertyMapper;
import com.ilimitech.webapp.realstate.repository.PropertyRepository;
import com.ilimitech.webapp.realstate.service.FilesStorageService;
import com.ilimitech.webapp.realstate.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ImageController {

    @Autowired
    private FilesStorageService storageService;

    private final Path root = Paths.get("./uploaded-images");
    public static final String DOT = ".";
    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper mapper;

    @PostMapping("/upload/user/{userId}/item/{itemId}")
    public ResponseEntity<String> handleFileUpload(
            @PathVariable("userId") String userId,
            @PathVariable("itemId") String itemId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            Path path = saveFile(Paths.get(root.toString(), "user", userId, "item", itemId).toString(), file);
            PropertyDto propertyDto = mapper.toDto(propertyRepository.findById(Long.parseLong(userId)).get());
            propertyDto.getImageEntities().add(ImageDto.builder().name(path.toString()).build());
            propertyService.saveProperty(propertyDto);
            byte[] bytes = file.getBytes();
            String message = String.format("User: %s, Item: %s, File: %s, Size: %d bytes",
                    userId, itemId, file.getOriginalFilename(), bytes.length);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to read the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ResponseBody
    @GetMapping("/imagesAjax/user/{userId}/item/{itemId}")
    public List<ImageInfoDto> getImagesByUserIdAndItemId(
            @PathVariable("userId") String userId,
            @PathVariable("itemId") String itemId) {
        return getImageInfoDtos(userId, itemId);
    }

    @GetMapping("/images/user/{userId}/item/{itemId}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String userId,
                                             @PathVariable String itemId,
                                             @PathVariable String filename) {
        Resource file = storageService.loadByUserIdAndItemId(root, filename, userId, itemId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/images/delete/user/{userId}/item/{itemId}/file/{filename:.+}")
    public String deleteImage(@PathVariable String userId,
                              @PathVariable String itemId,
                              @PathVariable String filename, Model model) {
        boolean existed = storageService.delete(root, userId, itemId, filename);
        List<ImageInfoDto> imageInfoDtos = getImageInfoDtos(userId, itemId);
        model.addAttribute("images", imageInfoDtos);
        model.addAttribute("userId", userId);
        model.addAttribute("itemId", itemId);
        return "realstate/dashboard/images";
    }

    private List<ImageInfoDto> getImageInfoDtos(String userId, String itemId) {
        return storageService.loadAllByUserIdAndItemId(root, Long.parseLong(userId), Long.parseLong(itemId))
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(ImageController.class, "getImage", userId, itemId, path.getFileName().toString()).build().toString();

                    return new ImageInfoDto(filename, url);
                }).collect(Collectors.toList());
    }

    private Path saveFile(String uploadDir, MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(uploadDir, UUID.randomUUID() + DOT + Objects.requireNonNull(getFileExtension(file)));
        return Files.write(path, file.getBytes());
    }

    public String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return ""; // Retornar una cadena vacía si no hay extensión
    }
}
