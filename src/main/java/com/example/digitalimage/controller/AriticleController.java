package com.example.digitalimage.controller;


import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.model.entity.ArticleAndContent;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.service.AriticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;
import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章相关接口")
public class AriticleController extends BaseController {

    @Autowired
    AriticleService ariticleService;

    @ApiOperation("根据类别获得文章列表")
    @GetMapping("/get_list")
    public ApiRequestResponse<List<Article>> getList(@RequestParam String category){
        return ApiRequestResponse.success(this.ariticleService.select_by_category(category));
    }

    @ApiOperation("用户收藏点赞浏览")
    @PostMapping("add_behavior")
    public ApiRequestResponse addBehavior(@RequestBody UserArticle userArticle){
        this.ariticleService.addBehavior(userArticle);
        return renderSuccess();
    }
    @ApiOperation("获得全部文章列表")
    @GetMapping("/getAll")
    public ApiRequestResponse<List<Article>> getAll(){
        return ApiRequestResponse.success(this.ariticleService.getAll());
    }



    @ApiOperation("用户上传文章")
    @PostMapping("/publish")
    public ApiRequestResponse publish(@RequestBody ArticleAndContent articleAndContent){
        int ret = this.ariticleService.publish(articleAndContent);
        System.out.println(ret);
        if(ret>0)
            return renderSuccess();
        else
            return this.renderError("添加文章失败，请重试");
    }

    @GetMapping("/delete")
    public ApiRequestResponse delete(@RequestParam Long id){
        int ret = this.ariticleService.delete(id);
        if(ret>0)
            return renderSuccess();
        else
            return this.renderError("删除文章失败，请重试");
    }




    @ApiOperation("获取文章详情")
    @GetMapping("/get_detail")
    public ArticleAndComment getDetail(@RequestParam Long id){
        return this.ariticleService.getDetail(id);
    }
}

