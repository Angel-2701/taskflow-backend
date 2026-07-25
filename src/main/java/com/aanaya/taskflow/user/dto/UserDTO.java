package com.aanaya.taskflow.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    @Email
    @NotBlank
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
