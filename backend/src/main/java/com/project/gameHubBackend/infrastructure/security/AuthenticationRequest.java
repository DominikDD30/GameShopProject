package com.project.gameHubBackend.infrastructure.security;

import lombok.*;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    private String login;
    private String password;
}
