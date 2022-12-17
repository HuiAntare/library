package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.User;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin        //解决跨域
@RestController   //返回的数据都以json返回
@RequestMapping("/user")           //一级目录,任何子集目录都要带上此前缀
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/list")
    public Result list(){                        //查询user
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){                        //分页查询
        return Result.success(userService.page(userPageRequest));    //返回的是对象
    }
}
