package com.ilimitech.webapp.realstate.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LocationDto {

    private long id;
    private String name;
}
