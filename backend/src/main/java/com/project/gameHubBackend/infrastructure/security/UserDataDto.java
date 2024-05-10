package com.project.gameHubBackend.infrastructure.security;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataDto {
    private final int userId;
    private final String name;
    private final String surname;
    private final String username;
    private final String email;
    private final Boolean hasAdmin;
}
