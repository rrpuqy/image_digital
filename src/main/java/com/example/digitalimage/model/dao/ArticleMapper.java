package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;


@Mapper
public interface ArticleMapper {
    int deleteByPrimaryKey(Long artId);

    int insert(Article record);

    Long insertSelective(Article record);

    Article selectByPrimaryKey(Long artId);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> selectAll();

    List<Article> select_by_category(String category_name);

    int addVisitorNum(Long id);

    int addLike(Long id);

    int addCollect(Long id);

    int deleteLike(Long id);

    int deleteCollect(Long id);

    int getPublishNum(String start_date, String current_date, Long id);

    int getPublishNums(Long userId);
}