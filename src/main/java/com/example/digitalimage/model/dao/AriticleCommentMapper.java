package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AriticleCommentMapper {
    public  ArticleAndComment getById(Integer id);
    public Comment getCommentForArt(Integer id);
}
