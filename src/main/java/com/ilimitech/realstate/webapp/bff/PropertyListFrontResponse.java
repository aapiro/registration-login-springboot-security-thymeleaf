package com.ilimitech.realstate.webapp.bff;

import com.ilimitech.realstate.webapp.dto.PropertyDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PropertyListFrontResponse {

    private List<PropertyDto> properties;
}
