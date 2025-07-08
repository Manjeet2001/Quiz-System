package com.result.controller;

import com.result.entity.Result;
import com.result.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        return ResponseEntity.ok(resultService.createResult(result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Result> getResult(@PathVariable Long id) {
        return resultService.getResult(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Result> getAllResults() {
        return resultService.getAllResults();
    }
}

