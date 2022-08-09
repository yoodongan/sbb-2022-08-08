package com.ll.exam.sbb;

import com.ll.exam.sbb.article.ArticleDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private static List<ArticleDto> articleDtoList = new ArrayList<>();
    private static long lastId = 0;


    public static int count = -1;
    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method="POST" action="/page2">
                    <input type="number" name="age" placeholder="나이" />
                    <input type="submit" value="page2로 POST 방식으로 이동" />
                </form>
                """;
    }

    @PostMapping("/page2")     // page1 에서 form 에 값 입력 시, PostMapping 과 연결되어 page2 로 이동.
    @ResponseBody
    public String showPage2Post(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, POST 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/page2")          // 이것은 뒤에 queryStr 붙여서 들어가야 나온다.
    @ResponseBody
    public String showPage2Get(@RequestParam(defaultValue = "0") int age) {
        return """
                <h1>입력된 나이 : %d</h1>
                <h1>안녕하세요, GET 방식으로 오셨군요.</h1>
                """.formatted(age);
    }

    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }
    @GetMapping("/minus")
    @ResponseBody
    public int showMinus(int a, int b) {
        return a - b;
    }

    @GetMapping("/increase")
    @ResponseBody
    public int increaseNum() {
        return ++count;
    }

    @GetMapping("/gugudan")
    @ResponseBody
    public String showGugudan(int dan, int limit) {
//        return IntStream.rangeClosed(1, limit)
//                .mapToObj(i -> "%d * %d = %d".formatted(dan, i, dan*i))
//                .collect(Collectors.joining("<br>"));
        String str = "";
        for (int i = 1; i <= limit; i++) {
            str += "%d * %d = %d<br>".formatted(dan, i, dan*i);
        }
        return str;
    }

    @GetMapping("mbti")
    @ResponseBody
    public String showMbti(@RequestParam(defaultValue = "없음") String name) {
        if (name.equals("홍길동")) {
            return "INFP";
        }
        if (name.equals("홍길순")) {
            return "ENFP";
        }
        if (name.equals("임꺽정")) {
            return "INFJ";
        }
        return null;
    }
    @GetMapping("/saveSessionAge/{age}")
    @ResponseBody
    public int showSessionAge(HttpServletRequest req, @PathVariable int age) {
        HttpSession session = req.getSession();
        session.setAttribute("key1", age);
        return age;
    }
    @GetMapping("/saveSessionAge")
    @ResponseBody
    public int showSessionAge(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (int) session.getAttribute("key1");
    }
    // 8월 9일자 코드 시작.

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(@RequestParam(defaultValue = "0") String title, String body) {
        long id = ++lastId;
        articleDtoList.add(new ArticleDto(id, title, body));
        return "%d번글이 등록되었습니다.".formatted(id);
    }
    @GetMapping("/article/{id}")
    @ResponseBody
    public ArticleDto showArticle(@PathVariable int id) {
        ArticleDto articleDto = articleDtoList.get(id-1);
        return articleDto;
    }

    @GetMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(long id, String title, String body) {
        ArticleDto articleDto = articleDtoList.get((int) (id-1));
        articleDto.setTitle("title");
        articleDto.setBody("body");
        return "%d번글이 수정되었습니다.".formatted(id);
    }

    @GetMapping("/deleteArticle")
    @ResponseBody
    public String deleteArticle(long id) {
        ArticleDto articleDto = articleDtoList.get((int) (id-1));
        articleDtoList.remove(articleDto);
        return "%d번글이 삭제되었습니다.".formatted(id);
    }


}
