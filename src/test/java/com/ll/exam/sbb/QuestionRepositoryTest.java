package com.ll.exam.sbb;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;
    private static int sampleLastId;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    public static int createSampleData(QuestionRepository questionRepository) {
        Question q1 = new Question();
        q1.setSubject("질문1");
        q1.setContent("내용1");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("질문2");
        q2.setContent("내용2");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);

        return q2.getId();  // 2개 의 데이터 개수가 리턴된다고 보면 된다.
    }

    private void createSampleData() {
        sampleLastId = createSampleData(questionRepository);
    }

    public static void clearData(QuestionRepository questionRepository){
        questionRepository.deleteAll();
        questionRepository.truncateTable();
    }

    private void clearData() {
        clearData(questionRepository);
    }

    @Test
    void 저장() {
        Question q1 = new Question();
        q1.setSubject("질문1");
        q1.setContent("내용1");
        q1.setCreateDate(LocalDateTime.now());
        questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("질문2");
        q2.setContent("내용2");
        q2.setCreateDate(LocalDateTime.now());
        questionRepository.save(q2);


        assertThat(q1.getId()).isEqualTo(sampleLastId+1);  // 마지막 아이디 + 1
        assertThat(q2.getId()).isEqualTo(sampleLastId+2);  // 마지막 아이디 + 2
    }
    @Test
    void 삭제() {
        assertThat(questionRepository.count()).isEqualTo(sampleLastId);
        Question question = questionRepository.findById(1).get();
        questionRepository.delete(question);
        assertThat(questionRepository.count()).isEqualTo(sampleLastId-1);
    }
    @Test
    void 수정() {
        Question question = questionRepository.findById(1).get();
        question.setSubject("Spring-Question-1");
        questionRepository.save(question);
        Question modifiedQuestion = questionRepository.findById(1).get();
        assertThat(modifiedQuestion.getSubject()).isEqualTo("Spring-Question-1");
    }
    @Test
    void findAll() {
        List<Question> allQuestions = questionRepository.findAll();
        assertThat(allQuestions.size()).isEqualTo(sampleLastId);
        Question q = allQuestions.get(0);
        assertThat(q.getSubject()).isEqualTo("질문1");
    }
    @Test
    void findBySubject() {
        Question q = questionRepository.findBySubject("질문1");
        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    void findBySubjectAndContent() {
        Question q = questionRepository.findBySubjectAndContent(
                "질문1", "내용1");
        assertThat(q.getId()).isEqualTo(1);
    }

    @Test
    void findBySubjectLike() {
        List<Question> qList = questionRepository.findBySubjectLike("질문%");
        Question q = qList.get(0);

        assertThat(q.getSubject()).isEqualTo("질문1");
    }
}
