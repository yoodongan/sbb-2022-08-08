package com.ll.exam.sbb;

import com.ll.exam.sbb.answer.Answer;
import com.ll.exam.sbb.answer.AnswerRepository;
import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SbbApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void saveQuestion() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	void modifyQuestion() {
		Optional<Question> oQuestion = questionRepository.findById(1);
		assertTrue(oQuestion.isPresent());
		Question question = oQuestion.get();
		question.setSubject("제목을 수정했습니다.");
		questionRepository.save(question);
	}

	@Test
	void deleteQuestion() {
		Optional<Question> oQuestion = questionRepository.findById(1);
		assertTrue(oQuestion.isPresent());
		Question question = oQuestion.get();
		questionRepository.delete(question);
		assertEquals(0, questionRepository.count());
	}

	@Test
	void addAnswer() {
		Optional<Question> oQuestion = questionRepository.findById(1);
		assertTrue(oQuestion.isPresent());
		Question question = oQuestion.get();

		Answer answer1 = new Answer();
		answer1.setContent("네 자동으로 생성됩니다.");
		answer1.setQuestion(question);
		answer1.setCreateDate(LocalDateTime.now());
		answerRepository.save(answer1);
	}

	@Transactional
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}






}
