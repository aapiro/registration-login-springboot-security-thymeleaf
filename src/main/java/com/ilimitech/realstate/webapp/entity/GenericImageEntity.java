package com.ilimitech.realstate.webapp.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "generic_images")
public class GenericImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "portal_generic_images_id_gen")
    @SequenceGenerator(name = "portal_generic_images_id_gen", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Size(max = 250)
    @Column(name = "url", length = 250)
    private String url;

    @Size(max = 250)
    @Column(name = "description", length = 250)
    private String description;

    @Size(max = 20)
    @Column(name = "image_type", length = 20)
    private String imageType;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = false;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity location;

}