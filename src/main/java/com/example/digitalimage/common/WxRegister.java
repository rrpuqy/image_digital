package com.example.digitalimage.common;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.ToString;

import javax.naming.ldap.PagedResultsControl;

@Data
@ToString
public class WxRegister {
    private String js_code;
    private Long userId;
    private String nick;
    private String avatarUrl;
    private Integer gender;
    private String userProvence;
    private String userCity;
}
