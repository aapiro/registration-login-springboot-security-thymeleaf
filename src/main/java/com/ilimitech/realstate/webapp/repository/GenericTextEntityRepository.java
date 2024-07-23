package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.GenericTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericTextEntityRepository extends JpaRepository<GenericTextEntity, Integer> {
}