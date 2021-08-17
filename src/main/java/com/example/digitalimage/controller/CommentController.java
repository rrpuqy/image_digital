package com.example.digitalimage.controller;


import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.dao.CommentMapper;
import com.example.digitalimage.model.entity.Comment;
import com.example.digitalimage.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Api(tags = "评论相关接口")
public class CommentController {

    @Autowired
    CommentService commentService;

//    @GetMapping("commentList")
//    List<Comment> showComment(){
//        return this.commentMapper.selectAll();
//    }

    @PostMapping("/add")
    @ApiOperation("发表评论")
    public ApiRequestResponse<Void> comment(@RequestBody Comment comment){
            this.commentService.add(comment);
            return ApiRequestResponse.success();
    }
}
