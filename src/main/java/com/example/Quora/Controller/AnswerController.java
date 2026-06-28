package com.example.Quora.Controller;


import com.example.Quora.DTOs.AnswerPostDTO;
import com.example.Quora.DTOs.AnswerResponseDTO;
import com.example.Quora.DTOs.AnswerUpdateDTO;
import com.example.Quora.Service.AnswerService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/questions")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/{questionId}/answer")
    public ResponseEntity<AnswerResponseDTO> postQuestion(@PathVariable Long questionId,@Valid @RequestBody AnswerPostDTO answerPostDTO){
        AnswerResponseDTO response=answerService.postAnswer(questionId,answerPostDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/answer/{answerId}")
    public ResponseEntity<AnswerResponseDTO> updateAns(@PathVariable Long answerId,@Valid @RequestBody AnswerUpdateDTO dto){
        AnswerResponseDTO response=answerService.updateAnswer(answerId,dto);
        return ResponseEntity.ok(response);
    }

}
