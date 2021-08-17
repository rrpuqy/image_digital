package com.example.digitalimage.service;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.dao.CommentMapper;
import com.example.digitalimage.model.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.digitalimage.common.ApiRequestResponse.success;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public void add(Comment comment){
//        throw new MyException(ExceptionEnum.INSERT_FAILED);
        int cnt = this.commentMapper.insert(comment);
        if(cnt == 0) throw new MyException(ExceptionEnum.INSERT_FAILED);
     }

}
