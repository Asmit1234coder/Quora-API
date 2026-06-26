package com.example.Quora.DTOs;

import jakarta.validation.constraints.Email;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class UpdateDTO {
    private String username;

    @Email(message = "Enter valid Email address")
    private String email;

    private String bio;

}
