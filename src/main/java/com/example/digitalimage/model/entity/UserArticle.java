package com.example.digitalimage.model.entity;

import java.util.Date;

public class UserArticle {
    private Long id;

    private Long userId;

    private Long artId;

    private Integer type;

    private Date generateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getArtId() {
        return artId;
    }

    public void setArtId(Long artId) {
        this.artId = artId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }
}