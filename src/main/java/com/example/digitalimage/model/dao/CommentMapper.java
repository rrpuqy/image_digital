package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface CommentMapper {
    int deleteByPrimaryKey(Integer comId);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer comId);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    List<Comment> selectAll();
}