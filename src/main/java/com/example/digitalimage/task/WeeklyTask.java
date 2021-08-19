package com.example.digitalimage.task;

import com.example.digitalimage.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeeklyTask {
    @Autowired
    TaskService taskService;

    @Scheduled(cron = "0 0 0 1/7 * ?")
    public void cron() {
        this.taskService.updateWeeklyTask();
    }
}
