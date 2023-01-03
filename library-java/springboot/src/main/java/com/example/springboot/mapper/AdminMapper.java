package com.example.springboot.mapper;

import com.example.springboot.common.LoginResult;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    List<Admin> list();     //返回所有数据的方法(接口)

    List<Admin> listByCondition(AdminPageRequest adminPageRequest);

    void save(Admin admin);

    Admin getById(Integer id);

    void updateById(Admin admin);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username,
                                   @Param("password") String password);

    int updatePassword(PassWordRequest passWordRequest);
}
