package com.ilimitech.webapp.realstate.entity;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link PropertyType}
 */
@Value
public class PropertyTypeDto implements Serializable {
    int id;
    String type;
    String icon;
    String name;
    int count;
    double delay;
}