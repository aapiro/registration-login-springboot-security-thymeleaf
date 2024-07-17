package com.ilimitech.webapp.realstate.administration.repository;

import com.ilimitech.webapp.realstate.administration.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
