package com.example.digitalimage.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {
    public static String getToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader("token");
        if (StringUtils.isNotEmpty(token)) {
            return token;
        }
        return null;
    }
}
