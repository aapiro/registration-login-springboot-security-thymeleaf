package com.ilimitech.webapp.realstate.bff;

import com.ilimitech.webapp.realstate.dto.PropertyDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PropertyListFrontResponse {

    private List<PropertyDto> properties;
}
