package com.ilimitech.realstate.webapp.controller;

import com.ilimitech.realstate.webapp.dto.ImageInfoDto;
import com.ilimitech.realstate.webapp.repository.PropertyRepository;
import com.ilimitech.realstate.webapp.service.FilesStorageService;
import com.ilimitech.realstate.webapp.service.PropertyService;
import com.ilimitech.realstate.webapp.util.Pair;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@AllArgsConstructor
public class ImageController {

    @Autowired
    private FilesStorageService storageService;
    private final Path root = Paths.get("./uploaded-images");


    private final PropertyService propertyService;
    private final PropertyRepository propertyRepository;
//    private final PropertyMapper mapper;


    @PostMapping("/upload/user/{userId}/item/{itemId}")
    @PreAuthorize("hasRole('APPUSER') or hasRole('ADMIN')")
    public ResponseEntity<String> handleFileUpload(
            @PathVariable("userId") @NotNull @Min(1) String userId,
            @PathVariable("itemId") @NotNull @Min(1) String itemId,
            @RequestParam("file") @NotNull MultipartFile file,
            @RequestParam("principal") boolean principalImage) throws IOException {

        if (file.isEmpty()) {
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        }
        Pair<String, HttpStatus> response = null;
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            response = propertyService.updateProperty(itemId, file, userId, userDetails, principalImage);
        } catch (IOException e) {
            log.error("Error: {}",e.toString());
            return new ResponseEntity<>("Failed to read the file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response.first(), response.second());
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
}
