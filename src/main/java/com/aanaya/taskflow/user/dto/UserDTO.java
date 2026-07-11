package com.aanaya.taskflow.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private String role;
}
