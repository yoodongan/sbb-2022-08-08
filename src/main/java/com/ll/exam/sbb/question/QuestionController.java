package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @RequestMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    /* 질문 저장 폼 보여주기와 질문 저장 POST */
    @GetMapping("/question/create")
    public String questionCreate() {
        return "question_form";
    }

    @PostMapping("/question/create")
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        // 질문 저장.
        questionService.create(subject, content);
        return "redirect:/question/list";
    }

}
