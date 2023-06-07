package com.kalidratorma.yss.security.auth;

import com.kalidratorma.yss.security.JwtService;
import com.kalidratorma.yss.security.user.Role;
import com.kalidratorma.yss.security.user.User;
import com.kalidratorma.yss.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CLIENT)
                .enabled(true)
                .build();
        repository.save(user);
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .exp(jwtService.extractExpiration(JwtToken))
                .role(user.getRole())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var JwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(JwtToken)
                .role(user.getRole())
                .exp(jwtService.extractExpiration(JwtToken))
                .build();
    }
}
