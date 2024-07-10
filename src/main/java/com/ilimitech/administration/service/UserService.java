package com.ilimitech.administration.service;

import com.ilimitech.administration.dto.UserDto;
import com.ilimitech.administration.entity.User;

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
