package com.ilimitech.webapp.realstate.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageInfoDto {
  
  private String name;
  private String url;

  public ImageInfoDto(String name, String url) {
    this.name = name;
    this.url = url;
  }

}
