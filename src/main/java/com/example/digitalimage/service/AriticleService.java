package com.example.digitalimage.service;

import com.example.digitalimage.model.dao.AriticleCommentMapper;
import com.example.digitalimage.model.entity.ArticleAndComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AriticleService {

    @Autowired
    AriticleCommentMapper ariticleCommentMapper;


    public ArticleAndComment getDetail(Integer id){
        ArticleAndComment articleAndComment = this.ariticleCommentMapper.getById(id);
        return articleAndComment;
    }
}
