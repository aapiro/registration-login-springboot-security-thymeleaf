package com.ilimitech.webapp.realstate.frontend.bff;

import com.ilimitech.webapp.realstate.entity.PropertyDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PropertyListFrontResponse {

    private List<PropertyDto> properties;
}
