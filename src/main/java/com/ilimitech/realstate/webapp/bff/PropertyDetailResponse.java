package com.ilimitech.realstate.webapp.bff;

import com.ilimitech.realstate.webapp.dto.PropertyDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * DTO for {@link PropertyDto}
 */
//@Value
@Builder
@Data
public class PropertyDetailResponse implements Serializable {
    String id;
    String title;
    String description;
    String address;
    String contactPhone;
    String email;
    String mapUrl;
    List<String> images;
    List<String> features;
}