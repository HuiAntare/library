package com.example.springboot.service;

import com.example.springboot.common.LoginResult;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> list();     //返回所有数据的方法(接口)    未实现-->impl里实现类

    Object page(AdminPageRequest adminPageRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void update(Admin admin);

    void deleteById(Integer id);

    LoginResult login(LoginRequest loginRequest);

    void changePass(PassWordRequest passWordRequest);
}

