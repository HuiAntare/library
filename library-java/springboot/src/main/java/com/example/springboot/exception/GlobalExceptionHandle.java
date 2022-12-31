package com.example.springboot.exception;

import com.example.springboot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {

    //全局异常处理
    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e){   //捕获到异常
        log.error("系统错误",e);  //后台打印
        return Result.error("系统错误");
    }
}
