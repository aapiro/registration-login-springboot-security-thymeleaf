package com.ilimitech.imagemanager.controller;

import com.ilimitech.webapp.realstate.dashboard.service.PropertyDashboardService;
import com.ilimitech.webapp.realstate.entity.ImageDto;
import com.ilimitech.webapp.realstate.entity.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyEntityMapper;
import com.ilimitech.webapp.realstate.frontend.repository.PropertyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RestController
//@RequestMapping("/upload")
public class FileUploadController {

//    @Value("${file.upload-dir}")
//    private String uploadDir;
    private final Path root = Paths.get("./uploads");
    public static final String DOT = ".";
    private final PropertyDashboardService propertyDashboardService;
    private final PropertyRepository propertyRepository;
    private final PropertyEntityMapper mapper;

    public FileUploadController(PropertyDashboardService propertyDashboardService, PropertyRepository propertyRepository, PropertyEntityMapper mapper) {
        this.propertyDashboardService = propertyDashboardService;
        this.propertyRepository = propertyRepository;
        this.mapper = mapper;
    }

    @PostMapping("/upload")
    public void upload(@RequestParam("file") MultipartFile file) {
        try {
            saveFile(root.toString(), file);
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @PostMapping("/upload/user/{userId}/item/{itemId}")
    public ResponseEntity<String> handleFileUpload(
            @PathVariable("userId") String userId,
            @PathVariable("itemId") String itemId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }

        try {
            Path path = saveFile(Paths.get("./upload", "user", userId, "item", itemId).toString(), file);
            PropertyDto propertyDto = mapper.toDto(propertyRepository.findById(Long.parseLong(userId)).get());
            propertyDto.getImages().add(ImageDto.builder().name(path.toString()).build());
            propertyDashboardService.saveProperty(propertyDto);
            byte[] bytes = file.getBytes();
            String message = String.format("User: %s, Item: %s, File: %s, Size: %d bytes",
                    userId, itemId, file.getOriginalFilename(), bytes.length);
            return new ResponseEntity<>(message, HttpStatus.OK);

        } catch (IOException e) {
            return new ResponseEntity<>("Failed to read the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Path saveFile(String uploadDir, MultipartFile file) throws IOException {
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Path path = Paths.get(uploadDir, UUID.randomUUID()+DOT+Objects.requireNonNull(getFileExtension(file)));
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