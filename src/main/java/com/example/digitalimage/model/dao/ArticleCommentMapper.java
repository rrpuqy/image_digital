package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentMapper {
    ArticleAndComment getById(Long id);
    Comment getCommentForArt(Long id);
    int addVisitorNum(Long id);
    int addThumpNum(Long id);
    int addCollectionNum(Long id);
    int decThumpNum(Long id);
    int decCollectionNum(Long id);
}
