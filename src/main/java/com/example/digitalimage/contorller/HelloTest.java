package com.example.digitalimage.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloTest {



    @GetMapping("hello")
    public String hello(){
        return "hello spring";
    }
}
