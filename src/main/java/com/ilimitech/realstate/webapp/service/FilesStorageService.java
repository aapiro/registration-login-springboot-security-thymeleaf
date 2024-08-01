package com.ilimitech.realstate.webapp.service;

import com.ilimitech.realstate.webapp.dto.ImageInfoDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public interface FilesStorageService {

  Path saveFileAndGetPath(String itemId, MultipartFile file, String userIdReq, boolean isPrincipalImage) throws IOException;

  @NotNull
  Path getImagePath(Path imageRootPath, String userId, String itemId, String filename, Boolean isPrincipal);

  Resource loadByUserIdAndItemId(String filename, String userId, String itemId, boolean isPrincipal);

  Path saveFile(String uploadDir, MultipartFile file) throws IOException;

  Stream<Path> loadAllByUserIdAndItemId(Path rootPath, long userId, long itemId, boolean principalImage);

  String getFileExtension(MultipartFile file);

  List<ImageInfoDto> getImageInfoDtos(String userId, String itemId, boolean principalImage);

  boolean delete(String userId, String itemId, String filename, boolean isPrincipal);
}
