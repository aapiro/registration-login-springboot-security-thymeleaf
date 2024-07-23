package com.ilimitech.realstate.webapp.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  @Override
  public Resource loadByUserIdAndItemId(Path rootPath, String filename, String userId, String itemId) {
    try {
      Path completePath = getImagePath(rootPath, filename, userId, itemId);

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

  private static @NotNull Path getImagePath(Path rootPath, String filename, String userId, String itemId) {
      return Paths.get(rootPath.toString(), "user", String.valueOf(userId), "item", String.valueOf(itemId), filename);
  }

  @Override
  public boolean delete(Path rootPath, String userId, String itemId, String filename) {
    try {
      Path completePath = getImagePath(rootPath, filename, userId, itemId);
      return Files.deleteIfExists(completePath);
    } catch (IOException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }
  @Override
  public Stream<Path> loadAllByUserIdAndItemId(Path rootPath, long userId, long itemId) {
    try{
      Path completePath = Paths.get(rootPath.toString(), "user", String.valueOf(userId), "item", String.valueOf(itemId));
      return Files.walk(completePath, 1).filter(path -> !path.equals(completePath)).map(completePath::relativize);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }
}
