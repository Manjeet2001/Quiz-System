package com.result.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    private Long userId;
    private String userName;
    private String userEmail;

    private Long quizId;
    private Integer score;
    private LocalDateTime attemptedAt;

    @ElementCollection
    private Map<Long, String> answer; // questionId -> selectedOption
}
