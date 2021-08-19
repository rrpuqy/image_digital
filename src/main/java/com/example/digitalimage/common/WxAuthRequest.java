package com.example.digitalimage.common;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component
public class WxAuthRequest {
    @Value("${wx.APPID}")
    private String appid;
    @Value("${wx.SECRET}")
    private String secret;
    private String js_code;
}
