package com.result.service;

import com.result.client.QuizClient;
import com.result.client.UserClient;
import com.result.dto.QuizDTO;
import com.result.dto.ResultDTO;
import com.result.dto.UserDTO;
import com.result.entity.Result;
import com.result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private QuizClient quizClient;

    @Autowired
    private UserClient userClient;

    public void processQuizResult(Long quizId) {
        QuizDTO quiz = quizClient.getQuizById(quizId);
        System.out.println("Quiz Title: " + quiz.getTitle());
    }

    public void fetchUserAndProcessResult(Long userId) {
        UserDTO user = userClient.getUserById(userId);
        System.out.println("Fetched user: " + user.getName());
    }

    public ResultDTO createResult(Result result) {
        Result saved = resultRepository.save(result);
        return mapToDto(saved);
    }

    public Optional<ResultDTO> getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .map(this::mapToDto);
    }

    public List<ResultDTO> getAllResults() {
        return resultRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private ResultDTO mapToDto(Result result) {
        ResultDTO dto = new ResultDTO();
        dto.setId(result.getResultId());
        dto.setUserId(result.getUserId());
        dto.setQuizId(result.getQuizId());
        dto.setScore(result.getScore());
        dto.setAttemptedAt(result.getAttemptedAt());
        return dto;
    }
}

