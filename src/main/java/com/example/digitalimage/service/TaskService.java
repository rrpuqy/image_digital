package com.example.digitalimage.service;

import com.example.digitalimage.common.Behavior;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.dao.*;
import com.example.digitalimage.model.entity.Task;
import com.example.digitalimage.model.entity.UserArticle;
import com.example.digitalimage.model.entity.UserTask;
import com.example.digitalimage.model.vo.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    UserTaskMapper userTaskMapper;

    @Autowired
    UserArticleMapper userArticleMapper;

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    TaskMapper taskMapper;

    @Autowired
    UserMapper userMapper;

    public List<TaskVo> getList(Long id){
        List<TaskVo> taskList = this.userTaskMapper.getList(id);
        for (int i = 0; i < taskList.size(); i++){
            TaskVo task = taskList.get(i);
            if (task.getUserTaskStatus() == 1 || task.getUserTaskStatus() == 2){
                continue;
            }
            else {
                int task_type = task.getTask().getType();
                int task_date = task.getTask().getTaskStatus();
                task.setNowNum(this.getNum(task_type, task_date, id));
                int tol_num = task.getTask().getTolNum();
                if (task.getNowNum() >= tol_num){
                    task.setUserTaskStatus(1);
                }
                else {
                    task.setUserTaskStatus(0);
                }
                this.userTaskMapper.updateByTaskIdAndUserId(task);
            }
        }
        return this.userTaskMapper.getList(id);
    }

    public int getNum(int task_type, int task_date, Long id) {
        SimpleDateFormat df_1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat df_2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

        Date start_date = new Date();
        Date current_date = start_date;

        String current_date_string = df_1.format(current_date);

        int date = start_date.getDate()-task_date+1;
        start_date.setDate(date);
        String start_date_string = df_2.format(start_date);

        if (task_type == Behavior.LOOK.getValue()) {
            return this.userArticleMapper.getLookNum(start_date_string,current_date_string,id);
        }
        else if (task_type == Behavior.PUBLISH.getValue()) {
            return this.articleMapper.getPublishNum(start_date_string,current_date_string,id);
        }
        else if (task_type == Behavior.COMMENT.getValue()) {
            return this.commentMapper.getCommentNum(start_date_string,current_date_string,id);
        }
        else if (task_type == Behavior.LOGIN.getValue()) {
            return this.userArticleMapper.getLoginNum(start_date_string,current_date_string,id);
        }
        else {
            throw new MyException(ExceptionEnum.SYSTEM_ERROR);
        }
    }

    public void updateDailyTask(){
        List<Integer> taskId_List = this.taskMapper.getDailyTaskId();
        for (int i = 0 ; i < taskId_List.size() ; i++){
            Integer taskId = taskId_List.get(i);
            this.userTaskMapper.updateDailyTask(taskId);
        }
    }
    public void updateWeeklyTask(){
        List<Integer> taskId_List = this.taskMapper.getWeeklyTaskId();
        for (int i = 0 ; i < taskId_List.size() ; i++){
            Integer taskId = taskId_List.get(i);
            this.userTaskMapper.updateWeeklyTask(taskId);
        }
    }

    public int finishTask(Long userId, Integer taskId){
        Task task = this.taskMapper.selectByPrimaryKey(taskId);
        Integer exp = task.getExp();
        Integer point = task.getPoint();
        int ret = this.userMapper.updateExpAndPoint(exp,point,userId);
        if (ret < 0){
            throw new MyException(ExceptionEnum.UPDATE_FAILED);
        }
        return this.userTaskMapper.finishTask(userId,taskId);
    }

    public void initTask(Long userId){
        List<Integer> taskId_List = this.taskMapper.getTaskId();
        for (int i = 0 ; i < taskId_List.size() ; i++) {
            Integer taskId = taskId_List.get(i);
            this.userTaskMapper.initTask(userId, taskId);
        }
    }
}