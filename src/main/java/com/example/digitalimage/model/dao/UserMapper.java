package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Article;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectLogin(UserVo userVo);

    List<Article> getCollect(Long id);

}