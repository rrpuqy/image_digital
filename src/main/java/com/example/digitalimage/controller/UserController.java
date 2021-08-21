package com.example.digitalimage.controller;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.common.Constant;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.vo.PersonalInfo;
import com.example.digitalimage.model.vo.RegisterVo;
import com.example.digitalimage.model.vo.UserProfile;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.UUID;

@RestController
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
            String token = tokenService.getToken(user.getUserId());
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

    @ApiOperation("获取历史记录")
    @GetMapping("/get_history")
    @ResponseBody
    public ApiRequestResponse<List<Article>> getHistory(@RequestParam Long id){
        return renderSuccess(this.userService.getHistory(id));
    }

    @ResponseBody
    @PostMapping("/admin/upload/file")
    public ApiRequestResponse<String> upload(HttpServletRequest httpServletRequest,
                                  @RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称UUID
        UUID uuid = UUID.randomUUID();
        String newFileName = uuid.toString() + suffixName;
        //创建文件
        File fileDirectory = new File(Constant.FILE_UPLOAD_DIR);
        File destFile = new File(Constant.FILE_UPLOAD_DIR + newFileName);
        System.out.println(destFile.getAbsolutePath());
        if (!fileDirectory.exists()) {
            if (!fileDirectory.mkdir()) {
                throw new MyException(ExceptionEnum.MKDIR_FAILED);
            }
        }
        try {
            file.transferTo(destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
//            System.out.println(getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/"
//                    + newFileName);
            String s = getHost(new URI(httpServletRequest.getRequestURL() + "")) + "/images/"
                    + newFileName;
            return ApiRequestResponse
                    .success(s);
        } catch (URISyntaxException e) {
            return ApiRequestResponse.error(ExceptionEnum.UPLOAD_FAILED);
        }
    }

    private URI getHost(URI uri) {
        URI effectiveURI;
        try {
            effectiveURI = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(),
                    null, null, null);
        } catch (URISyntaxException e) {
            effectiveURI = null;
        }
        return effectiveURI;
    }

    @ApiOperation("获取个人界面")
    @GetMapping("/get_personal_info")
    public ApiRequestResponse<PersonalInfo> getPersonalInfo(Long id){
        return this.renderSuccess(this.userService.getPersonalInfo(id));
    }

    @ApiOperation("获取用户资料")
    @GetMapping("/get_userprofile")
    public ApiRequestResponse<UserProfile> getUserProfile(Long id){
        return this.renderSuccess(this.userService.showUserProfile(id));
    }

    @ApiOperation("修改用户资料")
    @PostMapping("修改用户信息")
    public ApiRequestResponse editUserProfile(UserProfile userProfile){
        this.userService.editUserProfile(userProfile);
        return this.renderSuccess();
    }
}
