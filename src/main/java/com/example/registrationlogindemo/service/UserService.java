package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.dto.UserDto;
import com.example.registrationlogindemo.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByUserNameOrEmail(String userName, String email);

    List<UserDto> findAllUsers();

    UserDto findById(Long id);

    void updateUser(UserDto user);

    void delete(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto findByUsername(String username);

    UserDto findByUsernameProfile(String username);
}
