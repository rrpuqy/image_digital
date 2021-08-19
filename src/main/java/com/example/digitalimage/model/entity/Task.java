package com.example.digitalimage.model.entity;

import lombok.ToString;

@ToString
public class Task {
    private Integer taskId;

    private String content;

    private Integer taskStatus;

    private Integer type;

    private Integer tolNum;

    private Integer exp;

    private Integer point;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getTolNum() {
        return tolNum;
    }

    public void setTolNum(Integer tolNum) {
        this.tolNum = tolNum;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
}