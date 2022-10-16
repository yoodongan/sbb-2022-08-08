package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/question/list")     // 질문 목록 페이지
    public String showList(Model model) {
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping("/")   // root url
    public String root(Model model) {
        return "redirect:/question/list";
    }

    @GetMapping("/question/detail/{questionId}")
    public String showDetail(Model model, @PathVariable Long questionId) {
        Question question = questionService.findById(questionId);
        model.addAttribute("question", question);
        return "question_detail";
    }


}
