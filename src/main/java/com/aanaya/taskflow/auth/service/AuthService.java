package com.aanaya.taskflow.auth.service;

import com.aanaya.taskflow.auth.dto.LoginRequest;
import com.aanaya.taskflow.security.jwt.JwtService;
import com.aanaya.taskflow.security.user.CustomUserDetails;
import com.aanaya.taskflow.user.dto.UserResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        String token = jwtService.generateToken(userDetails.getUserId());

        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return new ResponseEntity<>(
                new UserResponseDTO(
                        userDetails.getUserId(),
                        userDetails.getUsername(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getRole(),
                        userDetails.getCreatedAt(),
                        userDetails.getUpdatedAt()
                ), HttpStatus.OK);
    }
}
