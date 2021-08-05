package com.example.digitalimage.common;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum ApiCode {
    SUCCESS(0, "成功"),
    NO_LOGIN(1002, "未登录或登录已失效"),
    BIZ_ERROR(1097, "通用业务处理错误");

    private Integer code;

    private String note;

    ApiCode(Integer code, String note) {
        this.code = code;
        this.note = note;
    }
}
