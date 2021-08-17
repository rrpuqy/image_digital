package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleCommentMapper {
    ArticleAndComment getById(Long id);
    Comment getCommentForArt(Long id);
//    int addVisitorNum(Long id);
//    int addThump(Long id);
}
