package com.ilimitech.imagemanager.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
  public void init();

  public void save(MultipartFile file);

  public Resource load(String filename);

  Resource loadByUserIdAndItemId(String filename, String userId, String itemId);

  public boolean delete(String userId, String itemId, String filename);
  
  public void deleteAll();

  public Stream<Path> loadAll();

  Stream<Path> loadAllByUserIdAndItemId(long userId, long itemId);
}
