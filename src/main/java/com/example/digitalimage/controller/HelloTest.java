package com.example.digitalimage.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测试接口")
public class HelloTest {



    @GetMapping("hello")
    public String hello(){
        return "hello spring";
    }
}
