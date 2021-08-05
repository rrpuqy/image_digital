package com.example.digitalimage.filter;



import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.service.TokenService;
import org.apache.ibatis.plugin.Intercepts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    TokenService tokenService;

    @Autowired
    UserMapper userMapper;

    @Value("${token.privateKey}")
    private String privateKey;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("token="+token);
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if (token == null  || "".equals(token.trim())) {
            throw new RuntimeException("无token，请重新登录");
        }
        Map<String, Object> map = tokenService.parseToken(token);
        System.out.println(map);
        Object userId = map.get("userId");
        System.out.println("--------------------------------------------------");
        System.out.println((Long)userId);
        User user = userMapper.selectByPrimaryKey((Long)userId);
        if (user == null){
            throw new RuntimeException("用户不存在，请重新登录");
        }
        return true;
    }
}
