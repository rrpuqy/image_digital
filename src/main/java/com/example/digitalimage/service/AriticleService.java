package com.example.digitalimage.service;

import com.example.digitalimage.model.dao.AriticleCommentMapper;
import com.example.digitalimage.model.dao.ArticleMapper;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.ArticleAndComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AriticleService {

    @Autowired
    AriticleCommentMapper ariticleCommentMapper;

    @Autowired
    ArticleMapper articleMapper;

    public ArticleAndComment getDetail(Long id){
        ArticleAndComment articleAndComment = this.ariticleCommentMapper.getById(id);
        return articleAndComment;
    }

    public List<Article> selectAll(){
        return  this.articleMapper.selectAll();
    }
}
