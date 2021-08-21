package com.example.digitalimage.model.vo;

import com.example.digitalimage.model.entity.Task;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskVo {

    private Long userId;

    private Integer taskId;

    /*
    现在已经完成的数目
     */
    private Integer nowNum;
    /*
    用户任务状态
     */
    private Integer userTaskStatus;

    private Task task;
}
