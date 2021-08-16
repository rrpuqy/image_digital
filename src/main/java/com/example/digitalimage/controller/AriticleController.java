package com.example.digitalimage.controller;


import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.ArticleAndComment;
import com.example.digitalimage.service.AriticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
@Api(tags = "文章相关api")
public class AriticleController extends BaseController {

    @Autowired
    AriticleService ariticleService;


    @GetMapping("/get_list")
    public List<Article> getList(@RequestParam String category){
        return this.ariticleService.select_by_category(category);
    }


    @GetMapping("/getAll")
    public List<Article> getAll(){
        return  this.ariticleService.getAll();
    }


    @GetMapping("/publish")
    public ApiRequestResponse publish(@RequestParam String title, @RequestParam String content, @RequestParam String category){
        int ret = this.ariticleService.publish(title,content,category);
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

