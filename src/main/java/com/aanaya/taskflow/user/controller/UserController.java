package com.aanaya.taskflow.user.controller;

import com.aanaya.taskflow.user.dto.UserDTO;
import com.aanaya.taskflow.user.dto.UserResponseDTO;
import com.aanaya.taskflow.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.save(userDTO), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
