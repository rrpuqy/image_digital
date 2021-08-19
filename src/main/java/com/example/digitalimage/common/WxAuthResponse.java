package com.example.digitalimage.common;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WxAuthResponse {
    private  String openid;
    private  String sessionKey;
    private  String unionid;
    private  Integer errcode;
    private  String errmsg;
}
