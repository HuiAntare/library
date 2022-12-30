package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.UserPageRequest;
import com.example.springboot.entity.Admin;
import com.example.springboot.service.IAdminService;
import com.example.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin        //解决跨域
@RestController   //返回的数据都以json返回
@RequestMapping("/admin")           //一级目录,任何子集目录都要带上此前缀
public class AdminController {

    @Autowired
    IAdminService adminService;

    @PostMapping ("/save")                 //新增接口
    public Result save(@RequestBody Admin admin){       //使用注解将json转成user对象
        adminService.save(admin);
        return Result.success();
    }

    @PutMapping("/update")                 //更新接口
    public Result update(@RequestBody Admin admin){
        adminService.update(admin);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}") //删除数据
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")                //根据id修改信息接口
    public Result getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/list")
    public Result list(){                        //查询user
        List<Admin> admins = adminService.list();
        return Result.success(admins);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){                        //分页查询
        return Result.success(adminService.page(adminPageRequest));    //返回的是对象
    }
}
