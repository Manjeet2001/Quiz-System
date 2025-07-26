package com.user.clients;

import com.user.dto.QuizDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "quiz-service", url = "http://localhost:8083")
public interface QuizClient {

    @GetMapping("/quiz/{quizId}")
    QuizDTO getQuizById(@PathVariable("quizId") Long quizId);

    @GetMapping("/quiz")
    List<QuizDTO> getAllQuizzes();
}

