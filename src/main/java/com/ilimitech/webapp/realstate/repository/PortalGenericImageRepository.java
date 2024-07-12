package com.ilimitech.webapp.realstate.repository;

import com.ilimitech.webapp.realstate.entity.GenericImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortalGenericImageRepository extends JpaRepository<GenericImageEntity, Integer> {
}