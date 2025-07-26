package com.user.entity;

import com.user.dto.QuizDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataResponse {
    private String result;
    private List<QuizDTO> quizzes;
}

