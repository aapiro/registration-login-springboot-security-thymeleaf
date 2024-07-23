package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.FeatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<FeatureEntity, Integer> {
}