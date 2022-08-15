package com.ll.exam.sbb;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.question.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void beforeEach() {
		Question q = new Question();
		q.setSubject("질문1");
		q.setContent("질문1의 내용1");
		q.setCreateDate(LocalDateTime.now());
		questionRepository.save(q);
	}

	@AfterEach
	void AfterEach() {
		questionRepository.disableForeignKeyCheck();
		questionRepository.truncateTable();
		questionRepository.enableForeignKeyCheck();
	}

	@Test
	void question_저장() {
		Question q = new Question();
		q.setSubject("질문2");
		q.setContent("질문2의 내용2");
		q.setCreateDate(LocalDateTime.now());
		questionRepository.save(q);
	}

	@Test
	void question_삭제() {
		Optional<Question> oq = questionRepository.findById(1);
		if(oq.isPresent()) {
			questionRepository.deleteById(1);
		}
		long count = questionRepository.count();
		Assertions.assertThat(count).isEqualTo(0);
	}
	@Test
	void question_수정() {
		Optional<Question> oq = questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("제목 수정1");
		questionRepository.save(q);
	}
	@Test
	void findBySubject() {
		Question q = questionRepository.findBySubject("질문1");
		assertEquals(1, q.getId());
	}
	@Test
	void findBySubjectAndContent() {
		Question q = questionRepository.findBySubjectAndContent("질문1", "질문1의 내용1");
		assertEquals(1, q.getId());
	}
	@Test
	void findBySubjectLike() {
		List<Question> qList = questionRepository.findBySubjectLike("질%");
		Question q = qList.get(0);
		assertEquals("질문1", q.getSubject());
	}

}
