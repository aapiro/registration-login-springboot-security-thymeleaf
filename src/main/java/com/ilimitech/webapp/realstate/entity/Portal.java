package com.ilimitech.webapp.realstate.entity;

import lombok.Data;

import java.util.List;

@Data
public class Portal {

    private String aboutTittle;
    private String description;
    private String aboutDescription;
    private List<String> carouselImages;

}
