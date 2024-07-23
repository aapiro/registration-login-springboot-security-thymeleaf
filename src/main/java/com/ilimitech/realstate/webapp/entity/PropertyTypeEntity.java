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
@Table(name = "property_type")
@NoArgsConstructor
@AllArgsConstructor
public class PropertyTypeEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
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