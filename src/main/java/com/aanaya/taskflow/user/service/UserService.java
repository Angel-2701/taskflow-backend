package com.aanaya.taskflow.user.service;

import com.aanaya.taskflow.exception.EmailAlreadyExistsException;
import com.aanaya.taskflow.user.dto.UserDTO;
import com.aanaya.taskflow.user.dto.UserResponseDTO;
import com.aanaya.taskflow.user.entity.User;
import com.aanaya.taskflow.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> save(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already registered"
            );
        }
        User user = new User();
        user.setRole(userDTO.getRole());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        userRepository.save(user);
        return new ResponseEntity<>("created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> allUsersDTO = userRepository.findAll().stream()
                .map(user -> new UserResponseDTO(
                        user.getId(),
                        user.getEmail(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getRole(),
                        user.getCreatedAt(),
                        user.getUpdatedAt()
                ))
                .toList();
        return new ResponseEntity<>(allUsersDTO, HttpStatus.OK);
    }
}
