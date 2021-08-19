package com.example.digitalimage.model.entity;

import lombok.ToString;

@ToString
public class UserTask {
    private Long id;

    private Long userId;

    private Integer taskId;

    private Integer nowNum;

    private Integer userTaskStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getNowNum() {
        return nowNum;
    }

    public void setNowNum(Integer nowNum) {
        this.nowNum = nowNum;
    }

    public Integer getUserTaskStatus() {
        return userTaskStatus;
    }

    public void setUserTaskStatus(Integer userTaskStatus) {
        this.userTaskStatus = userTaskStatus;
    }
}