package com.quiz.service;

import com.quiz.entity.Question;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(url = "http://localhost:8082", value = "Question-Client")
public interface QuestionClient {
    @GetMapping("/questions/bulk")
    List<Question> getQuestionsByIds(@RequestParam("ids") List<Long> ids);

}
