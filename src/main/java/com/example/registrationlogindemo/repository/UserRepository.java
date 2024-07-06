package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String userName, String Email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUserNameLikeIgnoreCase(String userName);
}
