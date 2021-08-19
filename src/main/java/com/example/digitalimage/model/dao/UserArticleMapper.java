package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserArticle record);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Long id);

    UserArticle selectByAll(UserArticle userArticle);

    int updateByPrimaryKeySelective(UserArticle record);

    int updateByPrimaryKey(UserArticle record);
}