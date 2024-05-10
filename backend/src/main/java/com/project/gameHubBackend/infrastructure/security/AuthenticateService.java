package com.project.gameHubBackend.infrastructure.security;

import com.project.gameHubBackend.domain.exception.BadRegisterData;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthenticateService {

    private final UserRepository userJpaRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        String regex = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{7,}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(request.getPassword());

        if (!matcher.matches()) {
           throw new RuntimeException("password not match requirements");
        }

        var user = User.builder()
                .username(request.getUsername())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .role(Role.USER)
                .build();
        try {
            userJpaRepository.save(user);
        }catch (RuntimeException e){
            throw new BadRegisterData();
        }
        return new AuthenticationResponse(jwtService.generateToken(user));
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getLogin(), request.getPassword())
            );
            var user = userJpaRepository.findByUsername(request.getLogin())
                    .orElseThrow(() -> new UsernameNotFoundException(
                            String.format("User with %s username not found", request.getLogin())));

            return new AuthenticationResponse(jwtService.generateToken(user));

    }

    public Optional<User> getUserByEmail(String email){
      return userJpaRepository.findByEmail(email);
    }

    public User getUserData(String token) {
        String username = jwtService.extractUsername(token);
       return userJpaRepository.findByUsername(username).get();
    }
}
