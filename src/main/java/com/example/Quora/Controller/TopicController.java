package com.example.Quora.Controller;

import com.example.Quora.DTOs.TopicRequestDTO;
import com.example.Quora.DTOs.TopicResponseDTO;
import com.example.Quora.Service.TopicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("topic")
public class TopicController
{
    private TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(@RequestBody TopicRequestDTO dto){
        TopicResponseDTO response=topicService.createTopic(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/test")
    public String test() {
        return "Working";
    }

    @GetMapping
    public ResponseEntity<List<TopicResponseDTO>> getAllTopics(){
        List<TopicResponseDTO> response= topicService.getAllTopics();
         return ResponseEntity.ok(response);
    }
}
