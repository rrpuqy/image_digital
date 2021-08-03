package com.example.digitalimage.common;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ApiRequestResponse <T>{
    private Integer status;
    private String msg;
    private  T data;
    private static final int OK_CODE = 10000;
    private static  final  String OK_MSG = "SUCCESS";

    public ApiRequestResponse(Integer status,String msg,T data){
        this.status = status;
        this.msg = msg;
        this.data =data;
    }

    public ApiRequestResponse(Integer status,String msg){
        this.status = status;
        this.msg = msg;
    }

    public ApiRequestResponse() {
         this(OK_CODE,OK_MSG);
    }

    public static <T> ApiRequestResponse<T> success(){
        return new ApiRequestResponse<>();
    }

    public static <T> ApiRequestResponse<T> success(T result){
        ApiRequestResponse<T> response = new ApiRequestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRequestResponse<T> error(Integer code, String msg){
        return  new ApiRequestResponse<>(code,msg);
    }

}
