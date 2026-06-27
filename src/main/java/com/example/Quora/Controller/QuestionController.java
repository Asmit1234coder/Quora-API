package com.example.Quora.Controller;

import com.example.Quora.DTOs.QuestionCreateDTO;
import com.example.Quora.DTOs.QuestionnResponseDTO;
import com.example.Quora.Entity.Question;
import com.example.Quora.Service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("/")
    public ResponseEntity<QuestionnResponseDTO> postQuestion(@RequestBody QuestionCreateDTO dto){
        QuestionnResponseDTO response=questionService.postQuestion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/search")
    public ResponseEntity<List<QuestionnResponseDTO>> searchQuestions(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String tag
    ){
        List<QuestionnResponseDTO> response=questionService.searchQuestions(text,tag);

        return ResponseEntity.ok(response);
    }
}
