package com.ilimitech.realstate.webapp.service;

import com.ilimitech.realstate.webapp.controller.ImageController;
import com.ilimitech.realstate.webapp.dto.ImageInfoDto;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  public static final String DOT = ".";
  private static final Path imageRootPath = Paths.get("./uploaded-images");
  public static final String USER = "user";
  public static final String ITEM = "item";
  public static final String PRINCIPAL = "principal";
  public static final String SECONDARY = "secondary";

  @Override
  public Resource loadByUserIdAndItemId(String filename, String userId, String itemId, boolean isPrincipal) {
    try {
      Path completePath = getImagePath(imageRootPath, userId, itemId, filename, isPrincipal);

      Resource resource = new UrlResource(completePath.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
  @Override
  public Path saveFileAndGetPath(String itemId, MultipartFile file, String userIdReq, boolean isPrincipalImage) throws IOException {
    Path path;
    if (isPrincipalImage) {
      path = saveFile(Paths.get(imageRootPath.toString(), USER, userIdReq, ITEM, itemId, PRINCIPAL).toString(), file);

    } else {
      path = saveFile(Paths.get(imageRootPath.toString(), USER, userIdReq, ITEM, itemId, SECONDARY).toString(), file);
    }
    return path;
  }
  @Override
  @NotNull
  public Path getImagePath(Path imageRootPath, String userId, String itemId, String filename, Boolean isPrincipal) {
    Path path;
    if (isPrincipal) {
      path = Paths.get(imageRootPath.toString(), USER, String.valueOf(userId), ITEM, String.valueOf(itemId), PRINCIPAL, filename);
    } else {
      path = Paths.get(imageRootPath.toString(), USER, String.valueOf(userId), ITEM, String.valueOf(itemId), SECONDARY, filename);
    }
    return path;
  }

  @Override
  public Stream<Path> loadAllByUserIdAndItemId(Path imageRootPath, long userId, long itemId, boolean principalImage) {
    try{
      Path completePath;
      if (principalImage) {
        completePath = Paths.get(imageRootPath.toString(), USER, String.valueOf(userId), ITEM, String.valueOf(itemId), PRINCIPAL);
      } else {
        completePath = Paths.get(imageRootPath.toString(), USER, String.valueOf(userId), ITEM, String.valueOf(itemId), SECONDARY);
      }
      return Files.walk(completePath, 1)
              .filter(path -> !path.equals(completePath))
              .map(completePath::relativize);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  @Override
  public boolean delete(String userId, String itemId, String filename, boolean isPrincipal) {
    try {
      Path completePath = getImagePath(imageRootPath, userId, itemId, filename, isPrincipal);
      return Files.deleteIfExists(completePath);
    } catch (IOException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
  @Override
  public Path saveFile(String uploadDir, MultipartFile file) throws IOException {
    File dir = new File(uploadDir);
    if (!dir.exists()) {
      dir.mkdirs();
    }

    Path path = Paths.get(uploadDir, UUID.randomUUID() + DOT + Objects.requireNonNull(getFileExtension(file)));
    return Files.write(path, file.getBytes());
  }
  @Override
  public String getFileExtension(MultipartFile file) {
    String originalFilename = file.getOriginalFilename();
    if (originalFilename != null && originalFilename.contains(DOT)) {
      return originalFilename.substring(originalFilename.lastIndexOf(DOT) + 1);
    }
    return "";
  }
  @Override
  public List<ImageInfoDto> getImageInfoDtos(String userId, String itemId, boolean isPrincipal) {
    return this.loadAllByUserIdAndItemId(imageRootPath, Long.parseLong(userId), Long.parseLong(itemId), isPrincipal)
            .map(path -> {
              String filename = path.getFileName().toString();
              String url = MvcUriComponentsBuilder
                      .fromMethodName(ImageController.class, "getImage", userId, itemId, path.getFileName().toString(),isPrincipal)
                      .build().toString();

              return new ImageInfoDto(filename, url);
            }).collect(Collectors.toList());
  }
}
