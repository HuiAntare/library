package com.example.springboot.controller;

import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.ICategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController   //返回的数据都以json返回
@RequestMapping("/category")           //一级目录,任何子集目录都要带上此前缀
public class BookController {

    @Resource
    ICategoryService categoryService;

    @PostMapping ("/save")                 //新增接口
    public Result save(@RequestBody Category category){       //使用注解将json转成admin对象
        categoryService.save(category);
        return Result.success();
    }

    @PutMapping("/update")                 //更新接口
    public Result update(@RequestBody Category category){
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")    //根据id删除数据接口
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")                //根据id返回信息接口
    public Result getById(@PathVariable Integer id){
        Category category = categoryService.getById(id);
        return Result.success(category);
    }

    @GetMapping("/list")
    public Result list(){                        //查询admins列表接口
        List<Category> admins = categoryService.list();
        return Result.success(admins);
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){                   //分页查询接口
        return Result.success(categoryService.page(categoryPageRequest));    //返回的是对象
    }
}
