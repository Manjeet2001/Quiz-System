package com.result.client;

import com.result.dto.QuizDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "quiz-service", url = "http://localhost:8083") // change port if needed
public interface QuizClient {

    @GetMapping("/quiz/{id}")
    QuizDTO getQuizById(@PathVariable("id") Long id);
}
