package com.example.Quora.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserDTO {

    @NotBlank(message = "username is required")
    private String username;

    @Email(message = "Enter valid email address")
    @NotBlank(message = "email is required")
    private String email;

    private String bio; // optional, so no @NotBlank
}
