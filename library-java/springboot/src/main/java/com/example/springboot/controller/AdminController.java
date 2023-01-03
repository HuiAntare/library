package com.example.springboot.controller;

import com.example.springboot.common.LoginResult;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.LoginRequest;
import com.example.springboot.controller.request.PassWordRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //返回的数据都以json返回
@RequestMapping("/admin")           //一级目录,任何子集目录都要带上此前缀
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping ("/login")                 //登录接口
    public Result login(@RequestBody LoginRequest loginRequest) {  //使用注解将json转成loginRequest对象
        LoginResult loginResult = adminService.login(loginRequest);
        return Result.success(loginResult);
    }

    @PutMapping ("/password")                 //修改密码接口
    public Result password(@RequestBody PassWordRequest passWordRequest) {  //使用注解将json转成passWordRequest对象
        adminService.changePass(passWordRequest);
        return Result.success();
    }

        @PostMapping ("/save")                 //新增接口
    public Result save(@RequestBody Admin admin){       //使用注解将json转成admin对象
        adminService.save(admin);
        return Result.success();
    }

    @PutMapping("/update")                 //更新接口
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")    //根据id删除数据接口
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")                //根据id返回信息接口
    public Result getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/list")
    public Result list(){                        //查询admins列表接口
        List<Admin> admins = adminService.list();
        return Result.success(admins);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){                   //分页查询接口
        return Result.success(adminService.page(adminPageRequest));    //返回的是对象
    }
}
