package com.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question{
    private Long id;

    private String content;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;

    private String category;
    private Integer difficultyLevel;

}
