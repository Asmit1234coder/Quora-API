package com.example.Quora.Repository;

import com.example.Quora.Entity.Topic;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.Function;

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

}
