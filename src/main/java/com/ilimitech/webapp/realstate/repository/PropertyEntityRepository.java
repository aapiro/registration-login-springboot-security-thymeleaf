package com.ilimitech.webapp.realstate.repository;

import com.ilimitech.webapp.realstate.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyEntityRepository extends JpaRepository<PropertyEntity, Integer> {
}