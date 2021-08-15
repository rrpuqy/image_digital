package com.example.digitalimage.model.entity;


import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class ArticleAndComment {
    private Long artId;

    private Long authId;

    private String title;

    private String publishdate;

    private String abstruct;

    private ArticleContent articleContent;

    private String comeFrom;

    List<Comment> commentList;

}
