package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Task;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(Integer taskId);

    int insert(Task record);

    int insertSelective(Task record);

    Task selectByPrimaryKey(Integer taskId);

    int updateByPrimaryKeySelective(Task record);

    int updateByPrimaryKey(Task record);

    List<Task> selectAll();

    List<Integer> getDailyTaskId();

    List<Integer> getWeeklyTaskId();

    List<Integer> getTaskId();
}