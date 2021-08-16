package com.example.digitalimage.controller;


import com.example.digitalimage.model.dao.CommentMapper;
import com.example.digitalimage.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    CommentMapper commentMapper;

    @GetMapping("commentList")
    List<Comment> showComment(){
        return this.commentMapper.selectAll();
    }
}
