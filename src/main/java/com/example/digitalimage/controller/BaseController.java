package com.example.digitalimage.controller;

import com.example.digitalimage.common.ApiCode;
import com.example.digitalimage.common.ApiRequestResponse;

public class BaseController {
    protected <T> ApiRequestResponse<T> renderSuccess(T data) {
        return new ApiRequestResponse<T>().success(data);
    }

    protected <T> ApiRequestResponse<T> renderSuccess() {
        return new ApiRequestResponse<T>().success();
    }


    protected <T> ApiRequestResponse<T> renderError(Integer code, String errMsg) {
        return new ApiRequestResponse<T>().error(code, errMsg);
    }

    protected <T> ApiRequestResponse<T> renderError(String errMsg) {
        return this.renderError(ApiCode.BIZ_ERROR.getCode(), errMsg);
    }
}
