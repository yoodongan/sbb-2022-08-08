package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;
    private final QuestionService questionService;

    @PostMapping("/answer/create/{questionId}")
    public String createAnswer(@PathVariable Long questionId, @RequestParam String content){
        Question question = questionService.findById(questionId);
        answerService.craete(question, content);
        return "redirect:/question/detail/%s".formatted(questionId);
    }

}
