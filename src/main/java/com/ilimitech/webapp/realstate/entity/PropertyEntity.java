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
import jakarta.persistence.OneToOne;
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

    @Column(name = "property_status")
    private String propertyStatus;

    @Column(name = "energy_qualification")
    private String energyQualification;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

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
    @JoinColumn(name = "property_type_id")
    private PropertyTypeEntity propertyType;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @OneToOne
    @JoinColumn(name = "features_id")
    private FeatureEntity features;

    @ManyToOne
    @JoinColumn(name = "property_contact_id")
    private PropertyContactEntity propertyContact;

    @OneToMany(mappedBy = "propertyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImageEntity> imageEntities = new ArrayList<>();

    @OneToMany(mappedBy = "propertyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ContactFormEntity> contactFormEntities = new ArrayList<>();
}