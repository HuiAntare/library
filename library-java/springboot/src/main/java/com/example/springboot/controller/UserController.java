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


    @PostMapping ("/save")                 //新增接口
    public Result save(@RequestBody User user){       //使用注解将json转成user对象
        userService.save(user);
        return Result.success();
    }

    @PutMapping("/update")                 //更新接口
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")   //根据id删除数据接口
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")                //根据id返回对象信息接口
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result list(){                        //查询users列表信息
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){             //分页查询
        return Result.success(userService.page(userPageRequest));    //返回的是对象
    }
}
