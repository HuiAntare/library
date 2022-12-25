package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class BaseRequest {         //基础公用请求
    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
