package com.quiz.dto;

import com.quiz.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {
    private Long quizId;
    private String title;
    private String description;
    private Integer duration;
    private List<Question> questions;

}

