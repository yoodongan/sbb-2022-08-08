package com.ll.exam.sbb.answer;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AnswerForm {
    @NotEmpty(message = "내용은 필수항목입니다!")
    private String content;
}
