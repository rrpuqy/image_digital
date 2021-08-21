package com.example.digitalimage.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.example.digitalimage.common.Rank;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;import com.example.digitalimage.model.dao.ArticleMapper;
import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.vo.*;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

import  com.example.digitalimage.common.Utils;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    @Autowired
    ArticleMapper articleMapper;
    public User login(UserVo userVo) {
        userVo.setPassword(DigestUtil.md5Hex(userVo.getPassword()));
        User user = userMapper.selectLogin(userVo);
        user.setPassword(null);
        return user;
    }

    public List<Article> getCollect(Long id){
        return  this.userMapper.getCollect(id);
    }
    public List<Article> getLike(Long id){
        return this.userMapper.getLike(id);
    }
    public List<UserArticle> getBehavior(Long userId,Long artId){
        return this.userMapper.getBehavior(userId,artId);
    }

    public List<Article> getHistory(Long id){
        return  this.userMapper.getHistory(id);
    }

    public int register(RegisterVo registerVo){
        User user = new User();
        log.info("registerVo",registerVo);
        BeanUtils.copyProperties(registerVo,user);
        log.info("user",user);
        User uold = userMapper.selectByPrimaryKey(registerVo.getUserId());
        if (uold!=null){
            throw new MyException(ExceptionEnum.CREATE_FAILED);
        }
        int count = userMapper.insertSelective(user);
        return count;
    }

    public void updateRemainingViewNum(){
        List<Long> userId_List = this.userMapper.getUserId();
        for (int i = 0 ; i < userId_List.size() ; i++){
            Long userId = userId_List.get(i);
            this.userMapper.updateRemainingViewNum(userId);
        }
    }

    public UserInfoVo getUserInfo(Long userId){
        UserInfoVo userInfoVo = new UserInfoVo();
        User user = this.userMapper.selectByPrimaryKey(userId);
        userInfoVo.setUserExp(user.getUserExp());
        userInfoVo.setUserPoint(user.getUserPoint());
        userInfoVo.setRemainingViewNum(user.getRemainingViewNum());
        userInfoVo.setUserArtNum(this.articleMapper.getPublishNums(userId));
        return userInfoVo;
    }

    public PersonalInfo getPersonalInfo(Long userId){
        PersonalInfo personalInfo = new PersonalInfo();
        User user = this.userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user,personalInfo);
        personalInfo.setRank(Utils.getLevel(user.getUserExp()));
        return personalInfo;
    }

    public UserProfile showUserProfile(Long userId){
        UserProfile userProfile = new UserProfile();
        User user = this.userMapper.selectByPrimaryKey(userId);
        BeanUtils.copyProperties(user,userProfile);
        return userProfile;
    }
    public void editUserProfile(UserProfile userProfile){
        User user = new User();
        BeanUtils.copyProperties(userProfile,user);
        this.userMapper.updateByPrimaryKeySelective(user);
    }


}

