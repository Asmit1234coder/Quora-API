package com.example.Quora.Controller;

import com.example.Quora.DTOs.RegisterUserDTO;
import com.example.Quora.DTOs.UpdateDTO;
import com.example.Quora.DTOs.UserResponseDTO;
import com.example.Quora.Entity.User;
import com.example.Quora.Service.UserService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
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

    @GetMapping("/get-user/{id}")
    public ResponseEntity<UserResponseDTO> getuser(@PathVariable Long id){
        UserResponseDTO response=userService.getUserDetails(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponseDTO> updateDetails(@PathVariable Long id, @RequestBody UpdateDTO dto){
        UserResponseDTO response=userService.updateProfile(id,dto);
        return ResponseEntity.ok(response);
    }
}

