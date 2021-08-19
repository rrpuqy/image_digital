package com.example.digitalimage.model.vo;

import com.example.digitalimage.model.entity.Task;
import lombok.Data;

@Data
public class TaskVo {

    private Long userId;

    private Integer taskId;

    private Integer nowNum;

    private Integer userTaskStatus;

    private Task task;
}
