package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable Integer id, @Valid AnswerForm answerForm, BindingResult result) {
        Question question = questionService.getQuestion(id);
        if (result.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";    // 질문 상세 페이지를 보여준다.
        }
        answerService.create(question, answerForm.getContent());
        return String.format("redirect:/question/detail/%s", id);
    }




}
