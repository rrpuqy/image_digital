package com.example.digitalimage.service;

import com.example.digitalimage.model.dao.AriticleCommentMapper;
import com.example.digitalimage.model.dao.ArticleContentMapper;
import com.example.digitalimage.model.dao.ArticleMapper;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.model.entity.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AriticleService {

    @Autowired
    AriticleCommentMapper ariticleCommentMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    public ArticleAndComment getDetail(Long id){
        ArticleAndComment articleAndComment = this.ariticleCommentMapper.getById(id);
        this.ariticleCommentMapper.addVisitorNum(id);
        return articleAndComment;
    }

    public List<Article> selectAll(){
        return  this.articleMapper.selectAll();
    }

    public List<Article> select_by_category(String category_name){
        return  this.articleMapper.select_by_category(category_name);
    }

    public int publish(String title, String content, String category){
        Article article = new Article();
        article.setTitle(title);
        //作者id待更新
        article.setAuthId(0L);
        article.setCategory(category);
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setPublishdate(df.format(day));
        this.articleMapper.insertSelective(article);
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArtId(article.getArtId());
        articleContent.setContent(content);
        int count = articleContentMapper.insert(articleContent);
        return count;
    }

    public int delete(Long id) {
        int a = this.articleMapper.deleteByPrimaryKey(id);
        int b = this.articleContentMapper.deleteByPrimaryKey(id);
        if (a > 0 && b > 0)
            return 1;
        else
            return 0;
    }
}
