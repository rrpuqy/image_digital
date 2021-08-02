package com.example.digitalimage.service;

import cn.hutool.crypto.digest.DigestUtil;
import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(UserVo userVo) throws Exception {
        userVo.setPassword(DigestUtil.md5Hex(userVo.getPassword()));
        User user = userMapper.selectLogin(userVo);
        if (user==null) {
            throw new Exception("error");
        }
        return user;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtil.md5Hex("1234"));
    }
}
