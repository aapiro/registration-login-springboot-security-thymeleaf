package com.ilimitech.webapp.realstate.entity;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link ImageEntity}
 */
@Value
@Builder
public class ImageDto implements Serializable {
    Long id;
    String name;
    String imageUrl;
}