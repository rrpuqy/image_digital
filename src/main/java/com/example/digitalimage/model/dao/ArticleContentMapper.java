package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.ArticleContent;

public interface ArticleContentMapper {
    int deleteByPrimaryKey(Long artId);

    int insert(ArticleContent record);

    int insertSelective(ArticleContent record);

    ArticleContent selectByPrimaryKey(Long artId);

    int updateByPrimaryKeySelective(ArticleContent record);

    int updateByPrimaryKeyWithBLOBs(ArticleContent record);
}