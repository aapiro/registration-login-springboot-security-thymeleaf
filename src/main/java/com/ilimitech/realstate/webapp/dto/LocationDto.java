package com.ilimitech.realstate.webapp.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LocationDto {

    private long id;
    private String name;
}
