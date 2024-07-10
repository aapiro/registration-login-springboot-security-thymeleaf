package com.ilimitech.webapp.realstate.frontend.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class ContactFormDto {

    @NotNull(message = "El campo propertyId no puede ser nulo")
    @NotEmpty(message = "El campo propertyId no puede estar vacío")
    private String propertyId;

    @NotNull(message = "El campo nombre no puede ser nulo")
    @NotEmpty(message = "El campo nombre no puede estar vacío")
    private String name;

    @NotNull(message = "El campo email no puede ser nulo")
    @NotEmpty(message = "El campo email no puede estar vacío")
    private String email;

    @NotNull(message = "El campo subject no puede ser nulo")
    @NotEmpty(message = "El campo subject no puede estar vacío")
    private String subject;

    @NotNull(message = "El campo message no puede ser nulo")
    @NotEmpty(message = "El campo message no puede estar vacío")
    private String message;

    private String captcha;

    private String hiddenCaptcha;

    private String realCaptcha;
}
