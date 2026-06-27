package com.example.Quora.Service;

import com.example.Quora.DTOs.QuestionCreateDTO;
import com.example.Quora.DTOs.QuestionnResponseDTO;
import com.example.Quora.Entity.Question;
import com.example.Quora.Entity.Topic;
import com.example.Quora.Entity.User;
import com.example.Quora.Repository.QuestionRepository;
import com.example.Quora.Repository.UserRepository;
import com.example.Quora.specification.QuestionSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final TopicService topicService;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository, TopicService topicService) {
        this.questionRepository = questionRepository;
        this.userRepository=userRepository;
        this.topicService = topicService;
    }

    public QuestionnResponseDTO postQuestion( QuestionCreateDTO questionCreateDTO){
        User user = userRepository.findById(questionCreateDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + questionCreateDTO.getUserId()));

        List<Topic> topics = questionCreateDTO.getTopicTags() == null
                ? List.of()
                : questionCreateDTO.getTopicTags().stream()
                .map(topicService::getOrCreateTopic)
                .toList();

        Question q=Question.builder()
                .title(questionCreateDTO.getTitle())
                .body(questionCreateDTO.getBody())
                .users(user)
                .topics(topics)
                .build();

        Question savedQuestion=questionRepository.save(q);

        return mapToResponseDTO(savedQuestion);
    }

    public List<QuestionnResponseDTO> searchQuestions(String text,String tag){
        Specification<Question> spec=Specification
                .where(QuestionSpecification.hasText(text))
                .and(QuestionSpecification.hasTag(tag));

        List<Question> questions=questionRepository.findAll(spec);

        return questions.stream().map(this::mapToResponseDTO).toList();
    }

    private QuestionnResponseDTO mapToResponseDTO(Question question) {
        return QuestionnResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .body(question.getBody())
                .createdAt(question.getCreatedAt())
                .userId(question.getUsers().getId())
                .username(question.getUsers().getUsername())
                .topicTags(question.getTopics().stream().map(Topic::getName).toList())
                .build();
    }
}
