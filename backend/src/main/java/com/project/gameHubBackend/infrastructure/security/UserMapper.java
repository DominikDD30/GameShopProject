package com.project.gameHubBackend.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public UserDataDto map(User user){
        Optional<? extends GrantedAuthority> adminRole = user.getAuthorities().stream()
                .filter(authority -> authority.getAuthority().equals("ADMIN")).findAny();

        return UserDataDto.builder()
                .userId(user.getId())
                .name(user.getFirstName())
                .surname(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .hasAdmin(adminRole.isPresent())
                .build();
    }
}
