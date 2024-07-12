package com.ilimitech.webapp.realstate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "location")
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_id_gen")
    @SequenceGenerator(name = "location_id_gen", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

}