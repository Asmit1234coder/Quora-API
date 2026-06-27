package com.example.Quora.DTOs;


import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionnResponseDTO {
    private Long id;
    private String title;
    private String body;
    private Date createdAt;
    private Long userId;
    private String username;
    private List<String> topicTags;
}
