package com.ll.exam.sbb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    /*
    private static int increasingNumber = -1;

    @GetMapping("/page1")
    @ResponseBody
    public String showFormPage() {
        return """
                <form method = "POST" action="/page2">
                  <input type="text" name = "name" placeholder ="이름을 입력해주세요."/>
                  <input type="submit" value = "page2 로 POST 이동"/>
                </form>
                """;
    }

    @PostMapping("/page2")
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "이름 없음") String d) {
        return """
                <h1>입력된 이름 : %s</h1>
                <h1>안녕하세요 %s님, POST 방식으로 오셨군요.</h1>
                """.formatted(d, d);
    }

    @GetMapping("/page2")
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") String name) {
        return """
                <h1>입력된 이름 : %s</h1>
                <h1>안녕하세요 %s님, POST 방식으로 오셨군요.</h1>
                """.formatted(name, name);
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(@RequestParam(defaultValue = "0") int a, int b) {
        return a + b;
    }

    @GetMapping("/increase")
    @ResponseBody
    public int increaseNum() {
        return ++increasingNumber;
    }

     */

    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }
}
