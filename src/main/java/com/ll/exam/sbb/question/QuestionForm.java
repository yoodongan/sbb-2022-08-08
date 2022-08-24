package com.ll.exam.sbb.question;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class QuestionForm {
    @NotEmpty(message = "제목은 필수로 입력해주세요!")
    @Size(max=200)
    private String subject;

    @NotEmpty(message = "내용은 필수로 입력해주세요!")
    private String content;
}
