package com.aanaya.taskflow.auth.service;

import com.aanaya.taskflow.auth.dto.LoginRequest;
import com.aanaya.taskflow.user.User;
import com.aanaya.taskflow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        User user = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid Credentials");
        }

        String token = "token";

        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
