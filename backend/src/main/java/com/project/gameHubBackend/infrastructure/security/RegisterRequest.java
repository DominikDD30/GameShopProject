package com.project.gameHubBackend.infrastructure.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @Size(min = 4)
    private String username;

    @Size(min = 3)
    private String firstName;

    @Size(min = 3)
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String password;

}
