package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AnswerRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void beforeEach() {
        clearTest();
        createSampleData();
    }

    void clearTest() {
        QuestionRepositoryTest.clearData(questionRepository);
        answerRepository.deleteAll();   // DELETE * FORM question; 이다.
        answerRepository.truncateTable();
    }

    void createSampleData() {
        QuestionRepositoryTest.createSampleData(questionRepository);

        Question q = questionRepository.findById(1).get();

        Answer a1 = new Answer();
        a1.setContent("sbb는 질문답변 게시판 입니다.");
        a1.setCreateDate(LocalDateTime.now());
        q.addAnswer(a1);

        Answer a2 = new Answer();
        a2.setContent("sbb에서는 주로 스프링부트관련 내용을 다룹니다.");
        a2.setCreateDate(LocalDateTime.now());
        q.addAnswer(a2);

        questionRepository.save(q);
    }

    @Test
    @Transactional
    @Rollback(false)
    void 저장() {
        Question q = questionRepository.findById(2).get();

        Answer answer1 = new Answer();
        answer1.setContent("SpringMVC 패턴 역시 객체지향 설계라 할 수 있습니다.");
        answer1.setCreateDate(LocalDateTime.now());
        q.addAnswer(answer1);

        Answer answer2 = new Answer();
        answer2.setContent("Winter 다음은 Spring입니다.");
        answer2.setCreateDate(LocalDateTime.now());
        q.addAnswer(answer2);

        questionRepository.save(q);   // cascade = CascadeType.ALL 을 사용해 Question 객체 저장 시 Answer 도 저장.
    }
    @Test
    @Transactional
    @Rollback(false)
    void 조회() {
        Answer a = this.answerRepository.findById(1).get();
        assertThat(a.getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }

    @Test
    @Transactional
    @Rollback(false)
    void 관련된_question_조회() {
        Answer a = this.answerRepository.findById(1).get();
        Question q = a.getQuestion();

        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    @Transactional
    @Rollback(false)
    void question으로부터_관련된_답변들_조회() {
        // SELECT * FROM question WHERE id = 1
        Question q = questionRepository.findById(1).get();
        // DB 연결이 끊김
        // SELECT * FROM answer WHERE question_id = 1
        List<Answer> answerList = q.getAnswerList();

        assertThat(answerList.size()).isEqualTo(2);
        assertThat(answerList.get(0).getContent()).isEqualTo("sbb는 질문답변 게시판 입니다.");
    }


}
