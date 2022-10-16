package com.ll.exam.sbb;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SbbApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;

	@Test
	@DisplayName("question, answer 저장 잘되는지 테스트")
	void t1() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해 알고 싶습니다!");
		q1.setCreatedDate(LocalDateTime.now());
		questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreatedDate(LocalDateTime.now());
		questionRepository.save(q2);

	}
	@Test
	@DisplayName("findAll 테스트")
	void t2() {
		List<Question> questions = questionRepository.findAll();
		Assertions.assertThat(questions.size()).isEqualTo(2);

		Question question = questions.get(0);
		Assertions.assertThat(question.getSubject()).isEqualTo("sbb가 무엇인가요?");
	}
	@Test
	@DisplayName("findBySubjectLike 테스트")
	void t3() {
		List<Question> questions = questionRepository.findBySubjectLike("sbb%");
		Assertions.assertThat(questions.size()).isEqualTo(1);
	}

	@Test
	@DisplayName("값 수정 테스트")
	void t4() {
		Optional<Question> oQuestion = questionRepository.findById(1L);
		Assertions.assertThat(oQuestion).isPresent();
		Question question = oQuestion.get();
		question.setSubject("수정된 질문입니다1");
		question.setContent("수정된 질문내용입니다1");
		questionRepository.save(question);

	}


	@Test
	void contextLoads() {
	}

}
