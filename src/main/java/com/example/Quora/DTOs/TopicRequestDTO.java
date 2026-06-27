package com.example.Quora.DTOs;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicRequestDTO {

    @NotBlank(message = "Please Enter a valid topic")
    private String name;
}
