package com.result.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {
    private Long id;
    private Long userId;
    private Long quizId;
    private Integer score;
    private LocalDateTime attemptedAt;
    private Map<Long, String> answers;
}

