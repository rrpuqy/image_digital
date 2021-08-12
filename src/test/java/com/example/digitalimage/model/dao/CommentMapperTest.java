package com.example.digitalimage.model.dao;

import com.example.digitalimage.model.entity.Comment;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Log4j2
class CommentMapperTest {

    @Autowired CommentMapper commentMapper;


    @Test
    void updateByPrimaryKey() {
    }

    @Test
    void selectAll() {
        PageHelper.startPage(3,2);
        List<Comment> commentList = (List<Comment>) this.commentMapper.selectAll();
        PageInfo pageInfo = new PageInfo(this.commentMapper.selectAll());
        log.info(pageInfo);
        System.out.println(commentList);
    }
}