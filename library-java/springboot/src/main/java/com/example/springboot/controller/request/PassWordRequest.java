package com.example.springboot.controller.request;

import lombok.Data;

@Data
public class PassWordRequest {
    private String username;
    private String password;
    //返回的用户和密码在数据库校验后就修改密码
    private String newPass;

}
