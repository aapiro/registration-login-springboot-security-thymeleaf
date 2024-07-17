package com.ilimitech.webapp.realstate.administration.security;

import com.ilimitech.webapp.realstate.administration.entity.Role;
import com.ilimitech.webapp.realstate.administration.entity.User;
import com.ilimitech.webapp.realstate.administration.repository.UserRepository;
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

        User user = userRepository.findFirstByUserNameContainingIgnoreCaseOrEmailContainingIgnoreCase(loginInput,loginInput);

        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getUserName(),
                    user.getPassword(),
                    mapRolesToAuthorities(user.getRoles()));
        }else{
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

