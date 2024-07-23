package com.ilimitech.realstate.webapp.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

public record PortalGenericImageDto(@Size(max = 50) String name, @Size(max = 250) String url,
                                    @Size(max = 20) String imageType) implements Serializable {
}