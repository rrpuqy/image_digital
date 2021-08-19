package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.UserTask;
import com.example.digitalimage.model.vo.TaskVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserTaskMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserTask record);

    int insertSelective(UserTask record);

    UserTask selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserTask record);

    int updateByPrimaryKey(UserTask record);

    List<TaskVo> getList(Long id);

    int updateByTaskIdAndUserId(TaskVo task);

    int updateDailyTask(Integer taskId);

    int updateWeeklyTask(Integer taskId);

    int finishTask(Long userId,Integer taskId);

    int initTask(Long userId,Integer taskId);
}