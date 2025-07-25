package com.result.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private Long quizId;
    private String title;
    private String description;
    private Integer duration;
    private List<Long> questionIds;
}
