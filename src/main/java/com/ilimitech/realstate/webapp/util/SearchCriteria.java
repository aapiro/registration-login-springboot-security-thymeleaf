package com.ilimitech.realstate.webapp.util;

import lombok.Data;

@Data
public class SearchCriteria {
    private String keyword;
    private String propertyType;
    private String location;
}
