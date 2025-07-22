package com.quiz.service;

import com.quiz.dto.QuizDTO;
import com.quiz.entity.Question;
import com.quiz.entity.Quiz;
import com.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;


    public List<Question> getQuestionsForQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId).orElseThrow();
        List<Long> questionIds = quiz.getQuestionIds();

        return questionClient.getQuestionsByIds(questionIds);
    }

    public QuizDTO createQuiz(QuizDTO quizDTO) {
        Quiz quiz = mapToEntity(quizDTO);
        Quiz savedQuiz = quizRepository.save(quiz);
        return mapToDto(savedQuiz);
    }

    public QuizDTO getQuiz(Long id) {
        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found with id: " + id));
        quiz.setQuestions(questionClient.getQuestionsByIds(quiz.getQuestionIds()));
        return mapToDto(quiz);
    }

    public List<QuizDTO> getAllQuiz() {
        List<QuizDTO> quizDTOS = quizRepository.findAll().stream().map(quiz -> {
            quiz.setQuestions(questionClient.getQuestionsByIds(quiz.getQuestionIds()));
            return mapToDto(quiz);
        }).collect(Collectors.toList());
        return quizDTOS;
    }

    public Quiz getQuizWithQuestions(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        List<Question> questionList = questionClient.getQuestionsByIds(quiz.getQuestionIds());
        quiz.setQuestions(questionList);
        return quiz;
    }


    private Quiz mapToEntity(QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizDTO.getQuizId());
        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setDuration(quizDTO.getDuration());
        if (quizDTO.getQuestions() != null) {
            List<Long> questionIds = quizDTO.getQuestions()
                    .stream()
                    .map(Question::getId)
                    .collect(Collectors.toList());
            quiz.setQuestionIds(questionIds);
        }
        return quiz;
    }

    public QuizDTO mapToDto(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setQuizId(quiz.getQuizId());
        dto.setTitle(quiz.getTitle());
        dto.setDescription(quiz.getDescription());
        dto.setDuration(quiz.getDuration());

        List<Question> questions = questionClient.getQuestionsByIds(quiz.getQuestionIds());
        dto.setQuestions(questions);

        return dto;
    }

}
