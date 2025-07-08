package com.result.service;

import com.result.entity.Result;
import com.result.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultService {
    @Autowired
    private ResultRepository resultRepository;

    public Result createResult(Result result) {
        return resultRepository.save(result);
    }

    public Optional<Result> getResult(Long resultId) {
        return resultRepository.findById(resultId);
    }

    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

}
