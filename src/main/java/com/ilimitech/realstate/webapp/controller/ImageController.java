package com.ilimitech.realstate.webapp.controller;

import com.ilimitech.realstate.webapp.dto.ImageInfoDto;
import com.ilimitech.realstate.webapp.service.FilesStorageService;
import com.ilimitech.realstate.webapp.service.PropertyService;
import com.ilimitech.realstate.webapp.util.Pair;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@AllArgsConstructor
public class ImageController {

    private FilesStorageService filesStorageService;
    private final PropertyService propertyService;

    @PostMapping("/upload/user/{userId}/item/{itemId}")
    @PreAuthorize("hasRole('APPUSER') or hasRole('ADMIN')")
    public ResponseEntity<String> uploadAndSaveImage(
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
            @PathVariable("itemId") String itemId,
            @RequestParam("principal") boolean principalImage){
        List<ImageInfoDto> imageInfoDtos = filesStorageService.getImageInfoDtos(userId, itemId, principalImage);
        return imageInfoDtos;
    }

    @GetMapping("/images/user/{userId}/item/{itemId}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String userId,
                                             @PathVariable String itemId,
                                             @PathVariable String filename,
                                             @RequestParam("principal") boolean isPrincipal) {

        Resource file = filesStorageService.loadByUserIdAndItemId(filename, userId, itemId, isPrincipal);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/images/delete/user/{userId}/item/{itemId}/file/{filename:.+}")
    public String deleteImage(@PathVariable String userId,
                              @PathVariable String itemId,
                              @PathVariable String filename, Model model,
                              @RequestParam("principal") boolean principalImage) {
        boolean existed = filesStorageService.delete(userId, itemId, filename, principalImage);
        List<ImageInfoDto> imageInfoDtos = filesStorageService.getImageInfoDtos(userId, itemId, principalImage);
        model.addAttribute("images", imageInfoDtos);
        model.addAttribute("userId", userId);
        model.addAttribute("itemId", itemId);
        return "realstate/dashboard/images";
    }

}
