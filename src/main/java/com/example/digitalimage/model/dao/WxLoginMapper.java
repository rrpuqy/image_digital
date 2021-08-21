package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.WxLogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WxLoginMapper {
    int deleteByPrimaryKey(String openid);

    int insert(WxLogin record);

    int insertSelective(WxLogin record);

    WxLogin selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(WxLogin record);

    int updateByPrimaryKey(WxLogin record);


}