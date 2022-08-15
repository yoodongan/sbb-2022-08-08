package com.ll.exam.sbb;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // 아래 Question 클래스는 엔티티 클래스이다.
// 아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.
public class Question {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;
    @Column(length = 200) // varchar(200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)   // cascade 속성 덕분에, Question 삭제 시 이에 딸린 Answer 하나하나 모두 삭제한다.
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(Answer answer) {   // 질문 1 : 답변 M  , 질문에서 답변을 추가하는 방식으로 변경.
        answer.setQuestion(this);
        getAnswerList().add(answer);

    }

}
