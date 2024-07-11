package com.ilimitech.webapp.realstate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "property_type")
public class PropertyTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "type")
    private String type;

    @Column(name = "icon")
    private String icon;

    @Column(name = "name")
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "delay", nullable = false)
    private double delay;

}