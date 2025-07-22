package com.quiz.service;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    public QuizDTO createQuiz(QuizDTO quizDTO) {
        Quiz quiz = mapToEntity(quizDTO);
        Quiz savedQuiz = quizRepository.save(quiz);
        return mapToDto(savedQuiz);
    }

    public QuizDTO getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        return mapToDto(quiz);
    }

    public List<QuizDTO> getAllQuiz() {
        return quizRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private Quiz mapToEntity(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizDTO.getQuizId());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setDuration(quizDTO.getDuration());
        quiz.setQuestionIds(quizDTO.getQuestionIds());
        return quiz;
    }

    private QuizDTO mapToDto(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setQuizId(quiz.getQuizId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setDuration(quiz.getDuration());
        dto.setQuestionIds(quiz.getQuestionIds());
        return dto;
    }
}
