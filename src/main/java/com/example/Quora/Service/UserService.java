package com.example.Quora.Service;


import com.example.Quora.DTOs.QuestionnResponseDTO;
import com.example.Quora.DTOs.RegisterUserDTO;
import com.example.Quora.DTOs.UpdateDTO;
import com.example.Quora.DTOs.UserResponseDTO;
import com.example.Quora.Entity.Question;
import com.example.Quora.Entity.User;
import com.example.Quora.Repository.QuestionRepository;
import com.example.Quora.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service

public class UserService{

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public UserService(UserRepository userRepository, QuestionRepository questionRepository) {
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
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

    public UserResponseDTO getUserDetails(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        return UserResponseDTO.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .email(user.getEmail())
                .build();
    }

    public UserResponseDTO updateProfile(Long id, UpdateDTO dto){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        if(dto.getEmail()!=null){
            user.setEmail(dto.getEmail());
        }
        if(dto.getBio()!=null){
            user.setBio((dto.getBio()));
        }
        if(dto.getUsername()!=null){
            user.setUsername(dto.getUsername());
        }
        User savedUser=userRepository.save(user);
        return UserResponseDTO.builder()
                .username(user.getUsername())
                .bio(user.getBio())
                .email(user.getEmail())
                .build();
    }

    public List<QuestionnResponseDTO> listAllQues(Long userId){
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User not found with id: " + userId);
        }

        List<Question> questions=questionRepository.findByUsers_Id(userId);

        return questions.stream()
                .map(this::mapToResponseDTO)
                .toList();


    }

    private QuestionnResponseDTO mapToResponseDTO(Question question) {
        return QuestionnResponseDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                        .build();

    }
}
