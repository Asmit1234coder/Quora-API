package com.example.Quora.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate //fill only when it is created
    protected Date createdAt;

    @OneToMany(mappedBy = "question")
    private List<Comments> comments=new ArrayList<>();

    @OneToMany(mappedBy = "questions")
    private List<Answers> answers=new ArrayList<>();

    @ManyToOne
    private User users;

    @ManyToMany
    @JoinTable(
            name = "question_topics",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id")
    )
    private List<Topic> topics = new ArrayList<>();

}
