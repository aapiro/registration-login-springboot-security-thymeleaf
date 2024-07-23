package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.webapp.entity.ContactFormEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactFormEntityRepository extends JpaRepository<ContactFormEntity, Long> {
}