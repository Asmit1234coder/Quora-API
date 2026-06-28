package com.example.Quora.DTOs;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerPostDTO {

    @NotNull(message = "userId is required")
    private Long userId;

    @NotBlank(message = "Cant' post blank answer")
    private String text;
}
