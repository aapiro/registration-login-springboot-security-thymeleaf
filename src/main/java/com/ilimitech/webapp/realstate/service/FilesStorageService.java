package com.ilimitech.webapp.realstate.service;

import org.springframework.core.io.Resource;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {

  Resource loadByUserIdAndItemId(Path rootPath, String filename, String userId, String itemId);

  boolean delete(Path rootPath, String userId, String itemId, String filename);

  Stream<Path> loadAllByUserIdAndItemId(Path rootPath, long userId, long itemId);
}
