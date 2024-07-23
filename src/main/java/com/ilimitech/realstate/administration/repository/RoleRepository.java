package com.ilimitech.realstate.administration.repository;

import com.ilimitech.realstate.administration.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Cacheable("roles")
    Role findByName(String name);
}
