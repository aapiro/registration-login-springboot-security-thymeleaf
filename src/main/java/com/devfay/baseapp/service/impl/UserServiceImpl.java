package com.devfay.baseapp.service.impl;

import com.devfay.baseapp.entity.UserEntity;
import com.devfay.baseapp.repository.RoleRepository;
import com.devfay.baseapp.dto.UserDto;
import com.devfay.baseapp.entity.Role;
import com.devfay.baseapp.mappers.UserMapper;
import com.devfay.baseapp.repository.UserRepository;
import com.devfay.baseapp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper;

    @Override
    public void saveUser(UserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);

        //encrypt the password once we integrate spring security
        //userEntity.setPassword(userDto.getPassword());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");//todo cambiar esto para que solo se puede crear usuarios como admin a los que sean clave
        if(role == null){
            role = checkRoleExist();
        }
        userEntity.setRoles(List.of(role));
        userRepository.save(userEntity);
    }

    public UserEntity findByUserNameOrEmail(String userName, String email) {
        return userRepository.findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(userName, email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserEntity not found")));
    }

    @Override
    public void updateUser(UserDto userDto) {
        UserEntity userEntity = userRepository.findById(userDto.getId()).orElseThrow();
        userRepository.save(userMapper.partialUpdate(userDto, userEntity));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUserName(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDto findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUserNameLikeIgnoreCase(username).orElseThrow());
    }
    @Override
    public UserDto findByUsernameProfile(String username) {
        return userMapper.toDtoProfile(userRepository
                .findByUserNameLikeIgnoreCase(username)
                .orElseThrow());
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}
