package com.example.digitalimage.model.entity;


import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class ArticleAndComment {
    private Integer artId;

    private Long authId;

    private String title;

    private Date publishdate;

    private String abstruct;

    private ArticleContent articleContent;

    List<Comment> commentList;


}
