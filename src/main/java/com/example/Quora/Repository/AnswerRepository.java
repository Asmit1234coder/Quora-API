package com.example.Quora.Repository;

import com.example.Quora.Entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answers,Long> {
}
