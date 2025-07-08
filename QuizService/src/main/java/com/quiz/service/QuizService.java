package com.quiz.service;

import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public Quiz createQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public Optional<Quiz> getQuiz(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getAllQuiz() {
        return quizRepository.findAll();
    }
}
