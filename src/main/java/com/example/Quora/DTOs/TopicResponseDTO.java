package com.example.Quora.DTOs;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicResponseDTO {
    private Long id;
    private String name;
}
