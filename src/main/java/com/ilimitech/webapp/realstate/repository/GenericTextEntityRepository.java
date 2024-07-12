package com.ilimitech.webapp.realstate.repository;

import com.ilimitech.webapp.realstate.entity.GenericTextEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericTextEntityRepository extends JpaRepository<GenericTextEntity, Integer> {
}