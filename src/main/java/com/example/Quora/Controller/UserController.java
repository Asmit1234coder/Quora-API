package com.example.Quora.Controller;

import com.example.Quora.DTOs.QuestionnResponseDTO;
import com.example.Quora.DTOs.RegisterUserDTO;
import com.example.Quora.DTOs.UpdateDTO;
import com.example.Quora.DTOs.UserResponseDTO;
import com.example.Quora.Entity.User;
import com.example.Quora.Service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody RegisterUserDTO dto) {
        UserResponseDTO response = userService.RegisterUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponseDTO> getuser(@PathVariable Long id){
        UserResponseDTO response=userService.getUserDetails(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("users/{userId}/questions")
    public ResponseEntity<List<QuestionnResponseDTO>> getAllQues(@PathVariable Long userId){
        List<QuestionnResponseDTO> response=userService.listAllQues(userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("usres/{id}/update")
    public ResponseEntity<UserResponseDTO> updateDetails(@PathVariable Long id, @RequestBody UpdateDTO dto){
        UserResponseDTO response=userService.updateProfile(id,dto);
        return ResponseEntity.ok(response);
    }


}

