package com.ilimitech.realstate.webapp.bff;

import com.ilimitech.realstate.webapp.dto.LocationDto;
import com.ilimitech.realstate.webapp.dto.PropertyDto;
import com.ilimitech.realstate.webapp.dto.PropertyTypeDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PropertyPrincipalFrontResponse {

    private List<PropertyDto> properties;
    private List<String> carouselImages;
    private List<PropertyTypeDto> categoriesTypes;
    private List<LocationDto> locationDtos;
}
