package com.example.digitalimage.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServlet;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    TokenService tokenService;

    public User login(UserVo userVo) {
        userVo.setPassword(DigestUtil.md5Hex(userVo.getPassword()));
        User user = userMapper.selectLogin(userVo);
        System.out.println(user);
        return user;
    }

}
