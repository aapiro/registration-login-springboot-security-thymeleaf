package com.ilimitech.realstate.webapp.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * DTO for {@link PropertyType}
 */
@Builder
@Data
public class PropertyTypeDto implements Serializable {
    int id;
    String type;
    String icon;
    String name;
    int count;
    double delay;
}