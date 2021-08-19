package com.example.digitalimage.controller;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.vo.RegisterVo;
import com.example.digitalimage.model.vo.UserVo;
import com.example.digitalimage.service.TokenService;
import com.example.digitalimage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

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

    @ApiOperation("获取收藏列表")
    @GetMapping("/get_collect")
    @ResponseBody
    public ApiRequestResponse<List<Article>> getCollect(@RequestParam Long id){
        return renderSuccess(this.userService.getCollect(id));
    }

    @ApiOperation("获取点赞列表")
    @GetMapping("/get_like")
    @ResponseBody
   public ApiRequestResponse<List<Article>> getLike(@RequestParam Long id){
        return renderSuccess(this.userService.getLike(id));
    }

    @ApiOperation("获取用户点赞收藏行为")
    @GetMapping("/get_behavior")
    @ResponseBody
    public ApiRequestResponse<List<UserArticle>> getBehavior(@ApiParam("用户id") @RequestParam Long userId,@ApiParam("文章id") @RequestParam Long artId){
        return renderSuccess(this.userService.getBehavior(userId,artId));
    }

}
