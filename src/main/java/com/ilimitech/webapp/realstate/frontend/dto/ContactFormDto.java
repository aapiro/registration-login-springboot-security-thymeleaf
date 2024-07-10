package com.ilimitech.webapp.realstate.frontend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactFormDto {
    private String propertyId;
    private String name;
    private String email;
    private String subject;
    private String message;
}
