package com.ilimitech.webapp.realstate.dto;

import lombok.Data;

import java.util.List;

@Data
public class PropertyDto {

    private int id;
    private String title;
    private String imageUrl;
    private String description;
    private StatusDto status;
    private boolean active;
    private String type;
    private double price;
    private double communityExpenses;
    private String address;
    private String addressLocality;
    private String addressWayName;
    private String addressWayNumber;
    private String addressPostalCode;
    private String operationType;
    private String propertyStatus;
    private String energyQualification;
    private boolean wardrobes;
    private boolean airConditioning;
    private boolean balcony;
    private boolean storageRoom;
    private boolean parkingPlace;
    private boolean pool;
    private boolean greenArea;
    private boolean orientationNorth;
    private boolean orientationSouth;
    private boolean orientationEast;
    private boolean orientationWest;
    private String contactName;
    private String contactPhone;
    private boolean cellphone;
    private boolean whatsapp;
    private boolean email;
    private boolean chat;
    private String contactPostalCode;
    private String houseType;
    private String houseCondition;
    private String mapUrl;
    private double constructedArea;
    private double usableArea;
    private double plotArea;
    private int numBedrooms;
    private int numBathrooms;
    private PropertyTypeDto propertyType;
    private PropertyContactDto propertyContact;
    List<ImageDto> imageEntities;
}
