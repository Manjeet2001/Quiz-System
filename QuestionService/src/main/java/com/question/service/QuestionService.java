package com.question.service;

import com.question.dto.QuestionDTO;
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

    public Question mapToEntity(QuestionDTO dto) {
        Question q = new Question();
        q.setId(dto.getId());
        q.setContent(dto.getContent());

        if (dto.getOptions() != null && dto.getOptions().size() == 4) {
            q.setOptionA(dto.getOptions().get(0));
            q.setOptionB(dto.getOptions().get(1));
            q.setOptionC(dto.getOptions().get(2));
            q.setOptionD(dto.getOptions().get(3));
        }

        q.setCorrectOption(dto.getCorrectOption());
        return q;
    }

    public QuestionDTO mapToDto(Question q) {
        QuestionDTO dto = new QuestionDTO();
        dto.setId(q.getId());
        dto.setContent(q.getContent());

        dto.setOptions(List.of(q.getOptionA(), q.getOptionB(), q.getOptionC(), q.getOptionD()));
        dto.setCorrectOption(q.getCorrectOption());

        return dto;
    }
}
