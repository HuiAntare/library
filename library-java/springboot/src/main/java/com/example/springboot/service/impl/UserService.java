package com.example.springboot.service.impl;

import cn.hutool.core.date.DateUtil;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import com.example.springboot.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired       //导入UserMapper,
    UserMapper userMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public Object page(UserPageRequest userPageRequest) {
        PageHelper.startPage(userPageRequest.getPageNum(),userPageRequest.getPageSize());  //插件处理数量和页数
        List<User> users = userMapper.listByCondition(userPageRequest);//条件查询
        return new PageInfo<>(users);
    }

    @Override             //新增
    public void save(User user) {
        Date date = new Date();
        //当做卡号去处理(采用日期＋唯一uuid生成)
        user.setUuid(DateUtil.format(date,"yyyy") + user.getUuid());
       userMapper.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }
}
