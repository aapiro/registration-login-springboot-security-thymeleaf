package com.ilimitech.webapp.realstate.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
  private final Path root = Paths.get("./uploads");

  @Override
  public void init() {
    try {
      Files.createDirectories(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())));
    } catch (Exception e) {
      if (e instanceof FileAlreadyExistsException) {
        throw new RuntimeException("A file of that name already exists.");
      }

      throw new RuntimeException(e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

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
  public Resource loadByUserIdAndItemId(String filename, String userId, String itemId) {
    try {
      Path completePath = getImagePath(filename, userId, itemId);

//      Path file = root.resolve(filename);
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

  private static @NotNull Path getImagePath(String filename, String userId, String itemId) {
      return Paths.get("./upload", "user", String.valueOf(userId), "item", String.valueOf(itemId), filename);
  }

  @Override
  public boolean delete(String userId, String itemId, String filename) {
    try {
      Path completePath = getImagePath(filename, userId, itemId);
      return Files.deleteIfExists(completePath);
    } catch (IOException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

  @Override
  public Stream<Path> loadAllByUserIdAndItemId(long userId, long itemId) {
    try{
      Path completePath = Paths.get("./upload", "user", String.valueOf(userId), "item", String.valueOf(itemId));
      return Files.walk(completePath, 1).filter(path -> !path.equals(completePath)).map(completePath::relativize);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }
}
