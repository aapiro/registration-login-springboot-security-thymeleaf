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
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "contact_form")
public class ContactFormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_form_entity_seq")
    @SequenceGenerator(name = "contact_form_entity_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_entity_id")
    private PropertyEntity propertyEntity;

    private String name;

    private String email;

    private String subject;

    private String message;

}