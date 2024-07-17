package com.ilimitech.webapp.realstate.controller;

import com.ilimitech.webapp.realstate.dto.ImageInfoDto;
import com.ilimitech.webapp.realstate.service.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ImageController {

    @Autowired
    FilesStorageService storageService;

//  @GetMapping("/")
//  public String homepage() {
//    return "redirect:/images";
//  }

    @GetMapping("/images/new")
    public String newImage(Model model) {
        return "realstate/dashboard/images";
    }

    @PostMapping("/images/upload")
    public String uploadImage(Model model, @RequestParam("file") MultipartFile file) {
        String message = "";

        try {
            storageService.save(file);

            message = "Uploaded the image successfully: " + file.getOriginalFilename();
            model.addAttribute("message", message);
        } catch (Exception e) {
            message = "Could not upload the image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            model.addAttribute("message", message);
        }

        return "realstate/dashboard/upload_form";
    }

    @GetMapping("/images/user/{userId}/item/{itemId}")
    public String getListImages(@PathVariable String userId,
                                @PathVariable String itemId,
            Model model) {
        List<ImageInfoDto> imageInfoDtos = storageService.loadAllByUserIdAndItemId(Long.parseLong(userId), Long.parseLong(itemId))
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(ImageController.class, "getImage", userId, itemId, path.getFileName().toString()).build().toString();

//                    List<ImageInfoDto> imageInfoDtos = storageService.loadAll().map(path -> {
//            String filename = path.getFileName().toString();
//            String url = MvcUriComponentsBuilder
//                    .fromMethodName(ImageController.class, "getImage", path.getFileName().toString()).build().toString();

            return new ImageInfoDto(filename, url);
        }).collect(Collectors.toList());

        model.addAttribute("images", imageInfoDtos);
        model.addAttribute("message", imageInfoDtos);

        return "realstate/dashboard/images";
    }

    @GetMapping("/imagesAjax")
    @ResponseBody
    public List<ImageInfoDto> getImages() {
        return storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(ImageController.class, "getImage", path.getFileName().toString()).build().toString();

            return new ImageInfoDto(filename, url);
        }).collect(Collectors.toList());
    }

    @GetMapping("/imagesAjax/user/{userId}/item/{itemId}")
    @ResponseBody
    public List<ImageInfoDto> getImagesByUserIdAndItemId(
            @PathVariable("userId") String userId,
            @PathVariable("itemId") String itemId) {
        return storageService.loadAllByUserIdAndItemId(Long.parseLong(userId), Long.parseLong(itemId))
                .map(path -> {
                    String filename = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(ImageController.class, "getImage", userId, itemId, path.getFileName().toString()).build().toString();

                    return new ImageInfoDto(filename, url);
                }).collect(Collectors.toList());
    }

    @GetMapping("/images/user/{userId}/item/{itemId}/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String userId,
                                             @PathVariable String itemId,
                                             @PathVariable String filename) {
        Resource file = storageService.loadByUserIdAndItemId(filename, userId, itemId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/images/delete/user/{userId}/item/{itemId}/file/{filename:.+}")
    public String deleteImage(@PathVariable String userId,
                              @PathVariable String itemId,
                              @PathVariable String filename, Model model) {
            boolean existed = storageService.delete(userId, itemId, filename);
        List<ImageInfoDto> imageInfoDtos = storageService.loadAllByUserIdAndItemId(Long.parseLong(userId), Long.parseLong(itemId))
                .map(path -> {
                    String filenameLeft = path.getFileName().toString();
                    String url = MvcUriComponentsBuilder
                            .fromMethodName(ImageController.class, "getImage", userId, itemId, path.getFileName().toString()).build().toString();
                    return new ImageInfoDto(filenameLeft, url);
                }).collect(Collectors.toList());
        model.addAttribute("images", imageInfoDtos);
        model.addAttribute("userId", userId);
        model.addAttribute("itemId", itemId);
        return "realstate/dashboard/images";
    }
}
