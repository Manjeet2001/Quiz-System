package com.question.controller;

import com.question.dto.QuestionDTO;
import com.question.entity.Question;
import com.question.repository.QuestionRepository;
import com.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO dto) {
        Question question = questionService.mapToEntity(dto);
        Question saved = questionService.createQuestion(question);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.mapToDto(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable Long id) {
        return questionService.getQuestion(id)
                .map(questionService::mapToDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = questionService.getAllQuestions().stream()
                .map(questionService::mapToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/bulk")
    public List<Question> getQuestionsByIds(@RequestParam("ids") List<Long> ids) {
        return questionService.getQuestionsByIds(ids);
    }

}

