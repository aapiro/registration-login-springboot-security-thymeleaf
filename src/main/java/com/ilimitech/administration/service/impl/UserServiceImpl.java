package com.ilimitech.administration.service.impl;

import com.ilimitech.administration.dto.UserDto;
import com.ilimitech.administration.entity.Role;
import com.ilimitech.administration.entity.User;
import com.ilimitech.administration.mappers.UserMapper;
import com.ilimitech.administration.repository.RoleRepository;
import com.ilimitech.administration.repository.UserRepository;
import com.ilimitech.administration.service.UserService;
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
        User user = userMapper.toEntity(userDto);

        //encrypt the password once we integrate spring security
        //user.setPassword(userDto.getPassword());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_ADMIN");//todo cambiar esto para que solo se puede crear usuarios como admin a los que sean clave
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    public User findByUserNameOrEmail(String userName, String email) {
        return userRepository.findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(userName, email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found")));
    }

    @Override
    public void updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow();
        userRepository.save(userMapper.partialUpdate(userDto,user));
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
