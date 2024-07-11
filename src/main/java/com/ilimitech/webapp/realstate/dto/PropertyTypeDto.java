package com.ilimitech.webapp.realstate.dto;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link PropertyType}
 */
@Value
@Builder
public class PropertyTypeDto implements Serializable {
    int id;
    String type;
    String icon;
    String name;
    int count;
    double delay;
}