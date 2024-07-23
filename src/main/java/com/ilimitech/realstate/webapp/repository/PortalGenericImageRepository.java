package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.GenericImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortalGenericImageRepository extends JpaRepository<GenericImageEntity, Integer> {
}