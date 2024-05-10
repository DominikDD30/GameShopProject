package com.project.gameHubBackend.api.controller;

import com.project.gameHubBackend.domain.exception.BadRegisterData;
import com.project.gameHubBackend.infrastructure.security.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import static org.apache.naming.ResourceRef.AUTH;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping(AUTH)
public class AuthenticationController {
    private static final String AUTH="/auth";

    private AuthenticateService authenticateService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        try {
            return ResponseEntity.ok(authenticateService.register(request));
        }catch (BadRegisterData exc){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        try {
            if (request.getLogin().contains("@")) {
                var user = authenticateService.getUserByEmail(request.getLogin()).orElseThrow(
                        () -> new UsernameNotFoundException(
                                String.format("User with %s email not found", request.getLogin()))
                );

                return ResponseEntity.ok(authenticateService.authenticate(request.withLogin(user.getUsername())));
            } else {
                return ResponseEntity.ok(authenticateService.authenticate(request));
            }
        }catch(UsernameNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        catch(RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/userData")
    public ResponseEntity<UserDataDto> getUserData(@RequestBody String token){
        try {
            User userData = authenticateService.getUserData(token);
            return ResponseEntity.ok(userMapper.map(userData));
        }catch (RuntimeException exc){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
