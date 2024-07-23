package com.ilimitech.realstate.webapp.repository;

import com.ilimitech.realstate.administration.entity.User;
import com.ilimitech.realstate.webapp.entity.UserContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserContactRepository extends JpaRepository<UserContactEntity, Long> {

    Optional<UserContactEntity> findFirstByUser(User user);
}