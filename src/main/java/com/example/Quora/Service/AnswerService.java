package com.example.Quora.Service;

import com.example.Quora.DTOs.AnswerPostDTO;
import com.example.Quora.DTOs.AnswerResponseDTO;
import com.example.Quora.Entity.Answers;
import com.example.Quora.Entity.Question;
import com.example.Quora.Entity.User;
import com.example.Quora.Repository.AnswerRepository;
import com.example.Quora.Repository.QuestionRepository;
import com.example.Quora.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }

    public AnswerResponseDTO postAnswer(Long questionId, AnswerPostDTO dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));


        Answers a= Answers.builder()
                .users(user)
                .text(dto.getText())
                .questions(question)
                .build();

        Answers savedAnswer=answerRepository.save(a);

        return mapToResponseDTO(savedAnswer);
    }

    private AnswerResponseDTO mapToResponseDTO(Answers savedAnswer) {
        return AnswerResponseDTO.builder()
                .id(savedAnswer.getId())
                .createdAt(savedAnswer.getCreatedAt())
                .userId(savedAnswer.getUsers().getId())
                .username(savedAnswer.getUsers().getUsername())
                .questionId(savedAnswer.getQuestions().getId())
                .questionTitle(savedAnswer.getQuestions().getTitle())
                .text(savedAnswer.getText())
                .build();

    }


}
