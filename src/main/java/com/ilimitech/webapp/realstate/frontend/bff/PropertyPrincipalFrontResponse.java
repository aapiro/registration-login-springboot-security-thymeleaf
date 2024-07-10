package com.ilimitech.webapp.realstate.frontend.bff;

import com.ilimitech.webapp.realstate.entity.Location;
import com.ilimitech.webapp.realstate.entity.PropertyDto;
import com.ilimitech.webapp.realstate.entity.PropertyType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PropertyPrincipalFrontResponse {

    private List<PropertyDto> properties;
    private List<String> carouselImages;
    private List<PropertyType> categoriesTypes;
    private List<Location> locations;
}
