package com.user.clients;

import com.user.dto.ResultDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "result-service", url = "http://localhost:8084")
public interface ResultClient {
    @GetMapping("/results/user/{userId}")
    public String getUserResult(@PathVariable("userId") Long userId);

    @PostMapping("/results")
    ResultDTO createResult(@RequestBody ResultDTO dto);
}
