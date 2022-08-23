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
		Question q = new Question();
		q.setSubject("sbb가 무엇인가요?");
		q.setContent("sbb에 대해서 알고 싶습니다.");
		q.setCreate_date(LocalDateTime.now());
		questionRepository.save(q);
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

		Answer answer = new Answer();
		answer.setContent("네 자동으로 생성됩니다.");
		answer.setQuestion(question);
		answer.setCreateDate(LocalDateTime.now());
		answerRepository.save(answer);
	}

	@Transactional
	@Test
	void testJpa() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswers();

		assertEquals(1, answerList.size());
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
	}






}
