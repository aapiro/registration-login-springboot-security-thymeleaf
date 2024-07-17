package com.devfay.baseapp.service;

import com.devfay.baseapp.dto.UserDto;
import com.devfay.baseapp.entity.UserEntity;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    UserEntity findByUserNameOrEmail(String userName, String email);

    List<UserDto> findAllUsers();

    UserDto findById(Long id);

    void updateUser(UserDto user);

    void delete(Long id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    UserDto findByUsername(String username);

    UserDto findByUsernameProfile(String username);
}
