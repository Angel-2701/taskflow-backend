package com.aanaya.taskflow.auth.controller;

import com.aanaya.taskflow.auth.dto.LoginRequest;
import com.aanaya.taskflow.auth.service.AuthService;
import com.aanaya.taskflow.user.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        return authService.getCurrentUser(authentication);
    }
}
