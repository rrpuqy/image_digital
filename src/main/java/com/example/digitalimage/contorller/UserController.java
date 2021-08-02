package com.example.digitalimage.contorller;

import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.UserVo;
import com.example.digitalimage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public User login(@RequestBody UserVo userVo) throws Exception {
        return userService.login(userVo);
    }
}
