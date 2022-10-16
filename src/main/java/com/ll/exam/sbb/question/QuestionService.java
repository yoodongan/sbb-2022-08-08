package com.ll.exam.sbb.question;

import com.ll.exam.sbb.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Long questionId) {
        Optional<Question> oQuestion = questionRepository.findById(questionId);
        if(oQuestion.isEmpty()) throw new DataNotFoundException("question not found");
        return oQuestion.get();
    }
}
