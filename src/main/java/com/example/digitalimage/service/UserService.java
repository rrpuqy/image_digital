package com.example.digitalimage.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.vo.RegisterVo;
import com.example.digitalimage.model.vo.UserVo;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;


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

}
