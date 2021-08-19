package com.example.digitalimage.controller;

import com.example.digitalimage.common.WxAuthRequest;
import com.example.digitalimage.common.WxAuthResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "测试接口")
public class HelloTest {

    @Autowired
    WxAuthRequest wxAuthRequest;

    @GetMapping("hello")
    public String hello(){
        return "hello spring";
    }

    @GetMapping("wxloginTest")
    public WxAuthResponse wxTest(@RequestParam String js_code){
        wxAuthRequest.setJs_code(js_code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid={APPID}&secret={SECRET}&js_code={JSCODE}&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> uriVariables = new HashMap<String,Object>();
        System.out.println("WxAuthRequest"+wxAuthRequest);
        uriVariables.put("APPID",wxAuthRequest.getAppid());
        uriVariables.put("SECRET",wxAuthRequest.getSecret());
        uriVariables.put("JSCODE",wxAuthRequest.getJs_code());
        WxAuthResponse wxAuthResponse= restTemplate.getForObject(url,WxAuthResponse.class,uriVariables);
        return wxAuthResponse;
    }
}
