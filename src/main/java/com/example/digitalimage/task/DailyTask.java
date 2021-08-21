package com.example.digitalimage.task;

import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.service.TaskService;
import com.example.digitalimage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyTask {
    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void cron() {
        this.taskService.updateDailyTask();
        this.userService.updateRemainingViewNum();
    }
}
