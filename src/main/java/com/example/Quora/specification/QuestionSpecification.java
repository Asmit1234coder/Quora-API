package com.example.Quora.specification;

import com.example.Quora.Entity.Question;
import com.example.Quora.Entity.Topic;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

public class QuestionSpecification {

    public static Specification<Question> hasText(String text) {
        return (root, query, criteriaBuilder) -> {
            if (text == null || text.isBlank()) {
                return criteriaBuilder.conjunction(); // means "no filter" — always true makes filter disappear when its param is null
            }
            String pattern = "%" + text.toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("body")), pattern)
            );
        };
    }

    public static Specification<Question> hasTag(String tag) {
        return (root, query, criteriaBuilder) -> {
            if (tag == null || tag.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            Join<Question, Topic> topicJoin = root.join("topics");
            return criteriaBuilder.equal(criteriaBuilder.lower(topicJoin.get("name")), tag.toLowerCase());
        };
    }
}
