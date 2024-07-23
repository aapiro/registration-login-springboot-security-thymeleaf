package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.PropertyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity, Integer> {
    Optional<PropertyTypeEntity> findByNameIgnoreCase(String name);
}