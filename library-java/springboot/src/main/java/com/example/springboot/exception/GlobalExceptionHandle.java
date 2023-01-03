package com.example.springboot.exception;

import cn.hutool.core.util.StrUtil;
import com.example.springboot.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandle {


    @ExceptionHandler(value = ServiceException.class)
    public Result serviceExceptionError(ServiceException e){   //捕获到异常
        log.error("业务异常",e);  //后台打印
        String code = e.getCode();
        if(StrUtil.isNotBlank(code)){    //先判断code有没有,若有执行
            return Result.error(code,e.getMessage());
        }
        //若没有则默认
        return Result.error(e.getMessage());
    }

    //全局异常处理
    @ExceptionHandler(value = Exception.class)
    public Result exceptionError(Exception e){   //捕获到异常
        log.error("系统错误",e);  //后台打印
        return Result.error("系统错误");
    }
}
