package com.example.Quora.DTOs;

import com.example.Quora.Entity.Topic;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionCreateDTO {

    @NotBlank(message = "userId is required")
    private Long userId;

    @NotBlank(message = "Please provide title for question")
    private String title;

    @NotBlank(message = "Body is required")
    private String body;

    private List<String> topicTags;

}
