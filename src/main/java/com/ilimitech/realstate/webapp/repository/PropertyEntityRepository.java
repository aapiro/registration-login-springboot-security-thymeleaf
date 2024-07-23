package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyEntityRepository extends JpaRepository<PropertyEntity, Integer> {
}