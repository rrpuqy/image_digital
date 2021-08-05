package com.example.digitalimage.contorller;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.UserVo;
import com.example.digitalimage.service.TokenService;
import com.example.digitalimage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    @ResponseBody
    public ApiRequestResponse<User> login(@RequestBody UserVo userVo, HttpServletResponse rq)  {
        User user = userService.login(userVo);
        if(user!=null){
            String token = tokenService.getToken(user);
            rq.setHeader("token",token);
            return this.renderSuccess(user);
        }

        return renderError("登录失败");
    }


}
