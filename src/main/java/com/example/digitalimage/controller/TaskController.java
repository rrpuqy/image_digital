package com.example.digitalimage.controller;

import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.model.dao.TaskMapper;
import com.example.digitalimage.model.dao.UserTaskMapper;
import com.example.digitalimage.model.entity.Task;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.vo.TaskVo;
import com.example.digitalimage.model.vo.UserInfoVo;
import com.example.digitalimage.service.TaskService;
import com.example.digitalimage.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
@Api(tags = "任务相关接口")
public class TaskController extends BaseController{

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @GetMapping("/getList")
    @ApiOperation("任务列表")
    public ApiRequestResponse<List<TaskVo>> getList(@RequestParam Long userId){
        return ApiRequestResponse.success(this.taskService.getList(userId));
    }

    @GetMapping("/finish")
    @ApiOperation("完成任务")
    public ApiRequestResponse finish(@RequestParam Long userId,@RequestParam Integer taskId){
        int ret = this.taskService.finishTask(userId, taskId);
        if (ret > 0){
            return this.renderSuccess();
        }
        else {
            return this.renderError("领取任务，请重试");
        }
    }

    @GetMapping("/init")
    @ApiOperation("初始化任务")
    public ApiRequestResponse init(@RequestParam Long userId){
        this.taskService.initTask(userId);
        return this.renderSuccess();
    }

    @GetMapping("/userInfo")
    @ApiOperation("获取个人信息")
    public ApiRequestResponse<UserInfoVo> userInfo(@RequestParam Long userId){
        return ApiRequestResponse.success(this.userService.getUserInfo(userId));
    }
//    @GetMapping("/test")
//    public int test(@RequestParam int a,@RequestParam int b,@RequestParam Long c){return this.taskService.getNum(a,b,c);}
}
