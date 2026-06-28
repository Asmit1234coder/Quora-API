package com.example.Quora.DTOs;


import com.example.Quora.Entity.Question;
import com.example.Quora.Entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponseDTO {
    private Long id;
    private Date createdAt;
    private String text;
    private Long userId;
    private String username;
    private Long questionId;
    private String questionTitle;


}
