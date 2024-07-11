package com.ilimitech.webapp.realstate.util;

import lombok.Data;

@Data
public class SearchCriteria {
    private String keyword;
    private String propertyType;
    private String location;
}
