package com.example.digitalimage.controller;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.RegisterVo;
import com.example.digitalimage.model.vo.UserVo;
import com.example.digitalimage.service.TokenService;
import com.example.digitalimage.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@Api(tags = "用户相关接口")
@RequestMapping("/user")
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
            rq.addHeader("Access-Control-Expose-Headers","token");
            return this.renderSuccess(user);
        }

        return renderError("登录失败");
    }

    @PostMapping("/register")
    @ResponseBody
    public ApiRequestResponse register(@Valid  @RequestBody RegisterVo registerVo){
        int  ret = userService.register(registerVo);
        if(ret>0)
            return renderSuccess();
        else
            return this.renderError("注册失败，请重试");
    }


    @GetMapping("/get_collect")
    @ResponseBody
    public ApiRequestResponse<List<Article>> getCollect(@RequestParam Long id){
        return renderSuccess(this.userService.getCollect(id));
    }

}
