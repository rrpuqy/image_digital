package com.example.digitalimage.controller;

import com.alibaba.fastjson.JSON;
import com.example.digitalimage.common.WxAuthRequest;
import com.example.digitalimage.common.WxAuthResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @GetMapping("hello")
    public String hello(){
        return "hello spring";
    }

    @GetMapping("wxloginTest")
    public WxAuthResponse wxTest(@RequestParam String js_code){
        wxAuthRequest.setJs_code(js_code);
        String url = "https://api.weixin.qq.com/sns/jscode2session?";

        Map<String,Object> uriVariables = new HashMap<String,Object>();
        uriVariables.put("appid",wxAuthRequest.getAppid());
        uriVariables.put("secret",wxAuthRequest.getSecret());
        uriVariables.put("js_code",wxAuthRequest.getJs_code());
        uriVariables.put("grant_type","authorization_code");
//        RestTemplate restTemplate = new RestTemplate();
//        WxAuthResponse wxAuthResponse= restTemplate.getForObject(url,WxAuthResponse.class,uriVariables);
//        restTemplate.getForEntity()
        String ret = sendPost(url,uriVariables);
        System.out.println(ret);
        WxAuthResponse wxAuthResponse = JSON.parseObject(ret,WxAuthResponse.class);
        return wxAuthResponse;
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
