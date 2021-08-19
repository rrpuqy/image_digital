package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserArticleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserArticle record);

    int insertSelective(UserArticle record);

    UserArticle selectByPrimaryKey(Long id);

    UserArticle selectByAll(UserArticle userArticle);

    int updateByPrimaryKeySelective(UserArticle record);

    int updateByPrimaryKey(UserArticle record);

    int getLookNum(String start_date, String current_date, Long id);

    int getLoginNum(String start_date, String current_date, Long id);

    List<Date> getAll();
}