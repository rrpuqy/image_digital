package com.example.digitalimage.model.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
@ApiModel("文章详情")
public class ArticleAndComment {

    @ApiModelProperty("文章id")
    private Long artId;

    private Long authId;

    private String title;

    private String publishdate;

    private String abstruct;

    private ArticleContent articleContent;

    private String comeFrom;

    List<Comment> commentList;

}
