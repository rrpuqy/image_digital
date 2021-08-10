package com.example.digitalimage.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AriticleServiceTest {

    @Autowired
    private AriticleService ariticleService;

    @Test
    void test(){
//        ariticleService = new AriticleService();
        System.out.println(ariticleService.getDetail(1));
    }

}