package com.user.service;

import com.user.clients.QuizClient;
import com.user.clients.ResultClient;
import com.user.dto.QuizDTO;
import com.user.entity.User;
import com.user.dto.UserDTO;
import com.user.entity.UserDataResponse;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResultClient resultClient;

    @Autowired
    private QuizClient quizClient;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public UserDataResponse fetchQuizzesAndResults(Long userId) {
        String result = resultClient.getUserResult(userId);
        List<QuizDTO> quizzes = quizClient.getAllQuizzes();
        return new UserDataResponse(result, quizzes);
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("User with id: " + id + "not found"));

        return mapToDto(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ResponseEntity<User> deleteUserWithId(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            userRepository.delete(user.get());
        }
        return ResponseEntity.ok().build();
    }

    public UserDTO mapToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }

}

