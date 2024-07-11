package com.ilimitech.webapp.realstate.bff;

import com.ilimitech.webapp.realstate.dto.LocationDto;
import com.ilimitech.webapp.realstate.dto.PropertyDto;
import com.ilimitech.webapp.realstate.dto.PropertyTypeDto;
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
