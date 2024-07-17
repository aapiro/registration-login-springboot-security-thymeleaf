package com.devfay.baseapp.repository;

import com.devfay.baseapp.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String userName, String Email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUserNameLikeIgnoreCase(String userName);
}
