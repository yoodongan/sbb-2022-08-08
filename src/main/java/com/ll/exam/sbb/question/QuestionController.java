package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController {
    private final QuestinoService questinoService;

    @GetMapping("/question/list")
    public String list(Model model) {
        List<Question> questionList = questinoService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
