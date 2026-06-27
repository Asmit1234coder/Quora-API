package com.example.Quora.Service;

import com.example.Quora.DTOs.TopicRequestDTO;
import com.example.Quora.DTOs.TopicResponseDTO;
import com.example.Quora.Entity.Topic;
import com.example.Quora.Repository.TopicRepository;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Service

public class TopicService {

    private final TopicRepository topicRepository;

    public TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public TopicResponseDTO createTopic(TopicRequestDTO dto) {

        Topic topic = Topic.builder()
                .Topic(dto.getName())
                .build();

        Topic savedTopic = topicRepository.save(topic);

        return TopicResponseDTO.builder()
                .id(savedTopic.getId())
                .name(savedTopic.getTopic())
                .build();
    }

    public List<TopicResponseDTO> getAllTopics(){
        return topicRepository.findAll()
                .stream()
                .map(topic -> mapToResponseDTO(topic))
                .toList();
    }

    private TopicResponseDTO mapToResponseDTO(Topic topic) {
        return TopicResponseDTO.builder()
                .id(topic.getId())
                .name(topic.getTopic())
                .build();
    }

}
