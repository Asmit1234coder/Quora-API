package com.example.Quora.Service;


import com.example.Quora.DTOs.RegisterUserDTO;
import com.example.Quora.DTOs.UserResponseDTO;
import com.example.Quora.Entity.User;
import com.example.Quora.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;


@Service

public class UserService{

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO RegisterUser(RegisterUserDTO dto) {

            if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                throw new RuntimeException("Email already registered");
            }
                User u = User.builder()
                .username(dto.getUsername())
                .bio(dto.getBio())
                .email(dto.getEmail())
                .build();

            User savedUser=userRepository.save(u);

            return UserResponseDTO.builder()
                    .id(savedUser.getId())
                    .username(savedUser.getUsername())
                    .email(savedUser.getEmail())
                    .bio(savedUser.getBio())
                    .build();

    }
}
