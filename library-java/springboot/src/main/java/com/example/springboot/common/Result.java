package com.example.springboot.common;

import lombok.Data;

@Data
public class Result {
    //静态常量
    private static final String SUCCESS_CODE="200";
    private static final String ERROR_CODE="-1";


    private String code;  //状态码
    private Object data;  //后台数据
    private String msg;   //存放错误的信息


    //返回状态(一般用来调试)
    public static Result success(){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        return result;
    }

    //返回状态和数据
    public static Result success(Object data){
        Result result = new Result();
        result.setCode(SUCCESS_CODE);
        result.setData(data);
        return result;
    }

    //返回错误信息
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }

}
