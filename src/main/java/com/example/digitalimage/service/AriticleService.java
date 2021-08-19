package com.example.digitalimage.service;

import com.example.digitalimage.common.Behavior;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.dao.ArticleCommentMapper;
import com.example.digitalimage.model.dao.ArticleContentMapper;
import com.example.digitalimage.model.dao.ArticleMapper;
import com.example.digitalimage.model.dao.UserArticleMapper;
import com.example.digitalimage.model.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AriticleService {

    @Autowired
    ArticleCommentMapper articleCommentMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleContentMapper articleContentMapper;

    @Autowired
    UserArticleMapper userArticleMapper;

    @Transactional(rollbackFor = Exception.class)
    public  void addBehavior(UserArticle userArticle) {
        int type = userArticle.getType();
        if(type == Behavior.LOOK.getValue()){
            articleMapper.addVisitorNum(userArticle.getArtId());
        }
        else if(type==Behavior.LIKE.getValue()) articleMapper.addLike(userArticle.getArtId());
        else if(type==Behavior.COLLECT.getValue()) articleMapper.addCollect((userArticle.getArtId()));
        else
            throw new MyException(ExceptionEnum.REQUEST_PARAM_ERROR);
        userArticle.setGenerateDate(new Date());
        userArticleMapper.insert(userArticle);
    }
    public ArticleAndComment getDetail(Long id){
        ArticleAndComment articleAndComment = this.articleCommentMapper.getById(id);
        return articleAndComment;
    }



    public List<Article> selectAll(){
        return  this.articleMapper.selectAll();
    }

    public List<Article> select_by_category(String category_name){
        return  this.articleMapper.select_by_category(category_name);
    }

    public List<Article> getAll(){
        return this.articleMapper.selectAll();
    }
    @Transactional
    public int publish(ArticleAndContent articleAndContent){
        Article article = new Article();
        article.setTitle(articleAndContent.getTitle());
        //作者id待更新
        article.setAuthId(articleAndContent.getAuthId());
        article.setCategory(articleAndContent.getCategory());
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setPublishdate(df.format(day));
        this.articleMapper.insertSelective(article);
        ArticleContent articleContent = new ArticleContent();
        articleContent.setArtId(article.getArtId());
        System.out.println("art_id="+article.getArtId());
        articleContent.setContent(articleAndContent.getContent());
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

    public int addThump(Long id){
        return this.articleCommentMapper.addThumpNum(id);
    }
    public int addCollection(Long id){
        return this.articleCommentMapper.addCollectionNum(id);
    }
    public int decThump(Long id){
        return this.articleCommentMapper.decThumpNum(id);
    }
    public int decCollection(Long id){
        return this.articleCommentMapper.decCollectionNum(id);
    }
}
