package com.ilimitech.webapp.realstate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generic_texts")
public class GenericTextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generic_texts_id_gen")
    @SequenceGenerator(name = "generic_texts_id_gen", sequenceName = "generic_texts_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "text", length = Integer.MAX_VALUE)
    private String text;

    @Size(max = 100)
    @Column(name = "description", length = 100)
    private String description;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}