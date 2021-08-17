package com.example.digitalimage.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("用户上传文章")
public class ArticleAndContent {
    @ApiModelProperty("文章id，不需要上传")
    private Long artId;
    private  Long authId;
    private String title;
    private String content;
    private String category;
}
