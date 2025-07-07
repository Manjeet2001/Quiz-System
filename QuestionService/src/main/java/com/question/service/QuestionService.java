package com.question.service;

import com.question.entity.Question;
import com.question.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> getQuestion(Long id) {
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }
}
