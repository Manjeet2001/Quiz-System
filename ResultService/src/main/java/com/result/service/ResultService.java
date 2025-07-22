package com.result.service;

import com.result.dto.ResultDTO;
import com.result.entity.Result;
import com.result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

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
        dto.setSubmittedAt(result.getSubmittedAt());
        return dto;
    }
}
