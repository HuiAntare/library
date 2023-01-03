package com.example.springboot.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.example.springboot.utils.TokenUtils;
import com.example.springboot.common.LoginResult;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.exception.ServiceException;
import com.example.springboot.mapper.AdminMapper;
import com.example.springboot.service.IAdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AdminService implements IAdminService {

    private static final String DEFAULT_PASSWORD = "123";
    private static final String PASSWORD_SALT = "****";

    @Autowired       //导入UserMapper,
    AdminMapper adminMapper;

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public Object page(AdminPageRequest adminPageRequest) {
        PageHelper.startPage(adminPageRequest.getPageNum(),adminPageRequest.getPageSize());  //插件处理数量和页数
        List<Admin> admins = adminMapper.listByCondition(adminPageRequest);//条件查询
        return new PageInfo<>(admins);
    }

    @Override             //新增
    public void save(Admin admin) {
        //默认密码: 123
        if(StrUtil.isBlank(admin.getPassword())){
            admin.setPassword(DEFAULT_PASSWORD);
        }
        admin.setPassword(securePassWord(admin.getPassword())); //设置md5加密+加盐
       adminMapper.save(admin);
    }

    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin admin) {
        admin.setUpdatetime(new Date());
        adminMapper.updateById(admin);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    @Override
    public LoginResult login(LoginRequest loginRequest) {
        loginRequest.setPassword(securePassWord(loginRequest.getPassword()));
        Admin admin = adminMapper.getByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
        if(admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        LoginResult loginResult = new LoginResult();
        BeanUtils.copyProperties(admin,loginResult);
        //生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginResult.setToken(token);
        return loginResult;
    }

    @Override
    public void changePass(PassWordRequest passWordRequest) {
        //要对新的密码加密
        passWordRequest.setNewPass(securePassWord(passWordRequest.getNewPass()));

        int count = adminMapper.updatePassword(passWordRequest);
        if(count <= 0){
            throw new ServiceException("修改密码失败");
        }
    }

    private String securePassWord(String password){
        return SecureUtil.md5(password + PASSWORD_SALT);
    }
}

