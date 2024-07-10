package com.ilimitech.imagemanager.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ImageInfo {
  
  private String name;
  private String url;

  public ImageInfo(String name, String url) {
    this.name = name;
    this.url = url;
  }

}
