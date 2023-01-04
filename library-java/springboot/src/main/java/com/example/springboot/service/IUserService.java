package com.example.springboot.service;

import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import java.util.List;

public interface IUserService {
    List<User> list();     //返回所有数据的方法(接口)    未实现-->impl里实现类

    Object page(UserPageRequest userPageRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);
    void handleAccount(User user);
}
