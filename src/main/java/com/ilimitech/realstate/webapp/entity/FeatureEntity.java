package com.ilimitech.realstate.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "features")
@NoArgsConstructor
@AllArgsConstructor
public class FeatureEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "air_conditioning")
    private Boolean airConditioning;

    @Column(name = "balcony")
    private Boolean balcony;

    @Column(name = "cellphone")
    private Boolean cellphone;

    @Column(name = "chat")
    private Boolean chat;

    @Column(name = "email")
    private Boolean email;

    @Column(name = "green_area")
    private Boolean greenArea;

    @Column(name = "orientation_east")
    private Boolean orientationEast;

    @Column(name = "orientation_north")
    private Boolean orientationNorth;

    @Column(name = "orientation_south")
    private Boolean orientationSouth;

    @Column(name = "orientation_west")
    private Boolean orientationWest;

    @Column(name = "parking_place")
    private Boolean parkingPlace;

    @Column(name = "pool")
    private Boolean pool;

    @Column(name = "storage_room")
    private Boolean storageRoom;

    @Column(name = "wardrobes")
    private Boolean wardrobes;

    @Column(name = "whatsapp")
    private Boolean whatsapp;

}