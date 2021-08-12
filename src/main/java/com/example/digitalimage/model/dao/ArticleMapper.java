package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long artId);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long artId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectAll();
}