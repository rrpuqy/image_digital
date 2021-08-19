package com.example.digitalimage.task;

import com.example.digitalimage.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DailyTask {
    @Autowired
    TaskService taskService;

    @Scheduled(cron = "0 0 0 1/1 * ?")
    public void cron() {
        this.taskService.updateDailyTask();
    }
}
