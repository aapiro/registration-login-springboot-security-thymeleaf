package com.ilimitech.realstate.webapp.bff;

import lombok.Data;

@Data
public class PropertyFormRequest {
    private int id;
    private String title;
    private String imageUrl;
    private String description;
    private boolean active;
    private String type;
    private double price;
    private double communityExpenses;
    private String addressLocality;
    private String addressWayName;
    private String addressWayNumber;
    private String addressPostalCode;
    private String operationType;
    //    private String propertyStatus; //todo este campo no queda claro
    //todo por crear tablas
    private String energyQualification;
    private String propertyType;
    private String propertyCondition;

    private String mapUrl;
    private double constructedArea;
    private double usableArea;
    private double plotArea;
    private int numBedrooms;
    private int numBathrooms;

    //features
    private boolean airConditioning;
    private boolean balcony;
    private boolean cellphone;
    private boolean chat;
    private boolean email;
    private boolean greenArea;
    private boolean orientationEast;
    private boolean orientationNorth;
    private boolean orientationSouth;
    private boolean orientationWest;
    private boolean parkingPlace;
    private boolean pool;
    private boolean storageRoom;
    private boolean wardrobes;
    private boolean whatsapp;
}
