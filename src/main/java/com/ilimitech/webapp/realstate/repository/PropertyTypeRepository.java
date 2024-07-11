package com.ilimitech.webapp.realstate.repository;

import com.ilimitech.webapp.realstate.entity.PropertyTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyTypeRepository extends JpaRepository<PropertyTypeEntity, Integer> {
}