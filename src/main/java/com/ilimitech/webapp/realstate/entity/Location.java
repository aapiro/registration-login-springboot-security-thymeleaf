package com.ilimitech.webapp.realstate.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Location {

    private long id;
    private String name;
}
