package com.ll.exam.sbb.article;

import lombok.Data;

@Data
public class ArticleDto {
    private long id;
    private String title;
    private String body;

    public ArticleDto(long id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
