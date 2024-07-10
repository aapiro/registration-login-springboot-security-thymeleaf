package com.ilimitech.webapp.realstate.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "property")
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "principal_image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "active")
    private boolean active;

    @Column(name = "type")
    private String type;

    @Column(name = "price")
    private double price;

    @Column(name = "community_expenses")
    private double communityExpenses;

    @Column(name = "address")
    private String address;

    @Column(name = "address_locality")
    private String addressLocality;

    @Column(name = "address_way_name")
    private String addressWayName;

    @Column(name = "address_way_number")
    private String addressWayNumber;

    @Column(name = "address_postal_code")
    private String addressPostalCode;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "property_status")
    private String propertyStatus;

    @Column(name = "energy_qualification")
    private String energyQualification;

    @Column(name = "wardrobes")
    private boolean wardrobes;

    @Column(name = "air_conditioning")
    private boolean airConditioning;

    @Column(name = "balcony")
    private boolean balcony;

    @Column(name = "storage_room")
    private boolean storageRoom;

    @Column(name = "parking_place")
    private boolean parkingPlace;

    @Column(name = "pool")
    private boolean pool;

    @Column(name = "green_area")
    private boolean greenArea;

    @Column(name = "orientation_north")
    private boolean orientationNorth;

    @Column(name = "orientation_south")
    private boolean orientationSouth;

    @Column(name = "orientation_east")
    private boolean orientationEast;

    @Column(name = "orientation_west")
    private boolean orientationWest;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "cellphone")
    private boolean cellphone;

    @Column(name = "whatsapp")
    private boolean whatsapp;

    @Column(name = "email")
    private boolean email;

    @Column(name = "chat")
    private boolean chat;

    @Column(name = "contact_postal_code")
    private String contactPostalCode;

    @Column(name = "house_type")
    private String houseType;

    @Column(name = "house_condition")
    private String houseCondition;

    @Column(name = "map_url", length = 500)
    private String mapUrl;

    @Column(name = "constructed_area")
    private double constructedArea;

    @Column(name = "usable_area")
    private double usableArea;

    @Column(name = "plot_area")
    private double plotArea;

    @Column(name = "num_bedrooms")
    private int numBedrooms;

    @Column(name = "num_bathrooms")
    private int numBathrooms;

    @ManyToOne
    @JoinColumn(name = "property_contact_entity_id")
    private PropertyContactEntity propertyContact;

    @OneToMany(mappedBy = "propertyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

}