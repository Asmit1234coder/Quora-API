package com.example.Quora.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "username is required")
    @Column(nullable = false)
    private String username;

    @Email(message = "Enter valid email address")
    @Column(nullable = false,unique = true)
    private String email;

    @Column(length = 100)
    private String bio;

    @OneToMany(mappedBy = "users")
    private List<Question> questions=new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Topic> topics=new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Answers> answers=new ArrayList<>();


}
