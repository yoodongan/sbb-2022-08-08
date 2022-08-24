package com.ll.exam.sbb.question;

import com.ll.exam.sbb.answer.Answer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Question {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)  // 질문 삭제 시, 참조하는 답변들도 모두 삭제된다는 뜻.
    private List<Answer> answerList = new ArrayList<>();
}
