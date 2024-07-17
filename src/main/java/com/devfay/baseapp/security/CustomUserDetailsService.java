package com.devfay.baseapp.security;

import com.devfay.baseapp.entity.UserEntity;
import com.devfay.baseapp.repository.UserRepository;
import com.devfay.baseapp.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginInput) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(loginInput,loginInput);

        if (userEntity != null) {
            return new org.springframework.security.core.userdetails.User(userEntity.getUserName(),
                    userEntity.getPassword(),
                    mapRolesToAuthorities(userEntity.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
        return mapRoles;
    }
}

