package com.example.digitalimage.controller;

import com.alibaba.fastjson.JSON;
import com.example.digitalimage.common.ApiRequestResponse;
import com.example.digitalimage.common.WxAuthRequest;
import com.example.digitalimage.common.WxAuthResponse;
import com.example.digitalimage.common.WxRegister;
import com.example.digitalimage.exception.ExceptionEnum;
import com.example.digitalimage.exception.MyException;
import com.example.digitalimage.model.dao.UserMapper;
import com.example.digitalimage.model.dao.WxLoginMapper;
import com.example.digitalimage.model.entity.User;
import com.example.digitalimage.model.entity.WxLogin;
import com.example.digitalimage.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@RestController
@Api(tags = "测试接口")
public class HelloTest {

    @Autowired
    WxAuthRequest wxAuthRequest;

    @Autowired
    TokenService tokenService;

    @Autowired
    WxLoginMapper wxLoginMapper;

    @Autowired
    UserMapper userMapper;
    @GetMapping("hello")
    public String hello(){
        return "hello spring";
    }

    @ApiOperation("尝试使用微信登录")
    @GetMapping("wxloginTest")
    public ApiRequestResponse wxTest(@RequestParam String js_code){
        wxAuthRequest.setJs_code(js_code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?";
//
//        Map<String,Object> uriVariables = new HashMap<String,Object>();
//        uriVariables.put("appid",wxAuthRequest.getAppid());
//        uriVariables.put("secret",wxAuthRequest.getSecret());
//        uriVariables.put("js_code",wxAuthRequest.getJs_code());
//        uriVariables.put("grant_type","authorization_code");
//        String ret = sendPost(url,uriVariables);
//        System.out.println(ret);
        System.out.println();
        String ret = getAppid();
        WxAuthResponse wxAuthResponse = JSON.parseObject(ret,WxAuthResponse.class);
        System.out.println(wxAuthResponse);
        String openid = wxAuthResponse.getOpenid();
        if(StringUtils.isEmpty(openid)) return ApiRequestResponse.error(1111,"未能获取到openid");
        WxLogin wxLogin = wxLoginMapper.selectByPrimaryKey(openid);
        if(wxLogin==null){
//            wxLogin = new WxLogin();
//            wxLogin.setOpenid(openid);
//            wxLogin.setSessionKey(wxAuthResponse.getSessionKey());
//            wxLoginMapper.insertSelective(wxLogin);
            return ApiRequestResponse.error(ExceptionEnum.NEED_WX_LOGIN);
        }
        else{
            SuccessRe successRe = new SuccessRe();
            successRe.setUserId(wxLogin.getUserId());
            successRe.setToken(tokenService.getToken(wxLogin.getUserId()));

            return  ApiRequestResponse.success(successRe);
        }
    }


    @ApiOperation("使用微信进行授权")
    @ResponseBody
    @PostMapping("/wxfirstlogin")
    @Transactional(rollbackFor = Exception.class)
    public ApiRequestResponse wxFirstLogin(@RequestBody WxRegister wxRegister){
//        if(wxLoginMapper.selectByPrimaryKey(wxRegister.getOpenid()).getUserId()!=null)
//            return ApiRequestResponse.error(111,"不是第一次微信登录");
        System.out.println(wxRegister);
        User user = new User();
        BeanUtils.copyProperties(wxRegister,user);
        System.out.println(user);

        WxLogin wxLogin = new WxLogin();
        wxAuthRequest.setJs_code(wxRegister.getJs_code());
        String ret = getAppid();
        WxAuthResponse wxAuthResponse = JSON.parseObject(ret,WxAuthResponse.class);
        if(wxAuthResponse.getOpenid()==null) throw new MyException(1111,"未能获取到openid");
        if(wxLoginMapper.selectByPrimaryKey(wxAuthResponse.getOpenid())!=null)
           throw  new MyException(234,"存在授权记录");
        userMapper.insertSelective(user);
        BeanUtils.copyProperties(wxAuthResponse,wxLogin);
        wxLogin.setUserId(wxRegister.getUserId());
        wxLoginMapper.insertSelective(wxLogin);
        return ApiRequestResponse.success(tokenService.getToken(wxRegister.getUserId()));
    }

    private String getAppid(){
        String url = "https://api.weixin.qq.com/sns/jscode2session?";
        Map<String,Object> uriVariables = new HashMap<String,Object>();
        uriVariables.put("appid",wxAuthRequest.getAppid());
        uriVariables.put("secret",wxAuthRequest.getSecret());
        uriVariables.put("js_code",wxAuthRequest.getJs_code());
        uriVariables.put("grant_type","authorization_code");
        String ret = sendPost(url,uriVariables);
        return ret;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    private String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // logger.error(e.getMessage(), e);
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}

@Data
class SuccessRe {

    private Long userId;
    private String token;
}
