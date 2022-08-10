package com.ll.exam.sbb.article;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ArticleDto {
    private static long lastId = 0;
    private long id;
    private String title;
    private String body;

    public ArticleDto(String title, String body) {
        this(++lastId, title, body);
    }
}
