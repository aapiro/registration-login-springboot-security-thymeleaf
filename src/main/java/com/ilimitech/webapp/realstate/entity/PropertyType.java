package com.ilimitech.webapp.realstate.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PropertyType {

    private int id;
    private String type;
    private String icon;
    private String name;
    private int count;
    private double delay;
}
