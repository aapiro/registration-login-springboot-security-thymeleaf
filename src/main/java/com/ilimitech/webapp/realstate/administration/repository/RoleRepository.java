package com.ilimitech.webapp.realstate.administration.repository;

import com.ilimitech.webapp.realstate.administration.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Cacheable("roles")
    Role findByName(String name);
}
