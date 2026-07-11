package com.aanaya.taskflow.user.service;

import com.aanaya.taskflow.user.User;
import com.aanaya.taskflow.user.dto.UserDTO;
import com.aanaya.taskflow.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ResponseEntity<String> save(UserDTO userDTO) {
        User user = new User();
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }
}
