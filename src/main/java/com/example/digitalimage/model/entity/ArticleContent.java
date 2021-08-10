package com.example.digitalimage.model.entity;


import lombok.ToString;

@ToString
public class ArticleContent {
    private Integer artId;

    private String content;

    public Integer getArtId() {
        return artId;
    }

    public void setArtId(Integer artId) {
        this.artId = artId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}