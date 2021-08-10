package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer artId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer artId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}