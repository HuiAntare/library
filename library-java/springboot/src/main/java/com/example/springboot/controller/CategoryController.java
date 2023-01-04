package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.common.Result;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController   //返回的数据都以json返回
@RequestMapping("/category")           //一级目录,任何子集目录都要带上此前缀
public class CategoryController {

    @Autowired
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

    @GetMapping("/tree")
    public Result tree(){                        //查询admins列表接口
        List<Category> list = categoryService.list();

        //对list操作即可
        //第一层
        //List<Category> treeList = list.stream().filter(v -> v.getPid() == null).collect(Collectors.toList());
        //递归实现children列表查询

        return Result.success(createTree(null,list));//null表示从第一级开始递归
    }

    //完全递归的方法实现递归树
    //pid:上一级的id作为下一集的pid
    private List<Category> createTree(Integer pid,List<Category> categories){
        List<Category> treeList = new ArrayList<>();
        for(Category category : categories){  //遍历所有的categories
            if(pid == null){
                if(category.getPid() == null){  //那这就是第一级节点
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(),categories));
                }
            }else{
                if(pid.equals(category.getPid())){
                    treeList.add(category);    //添加进treeList里面去
                    category.setChildren(createTree(category.getId(),categories));
                }
            }
            if(CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }
        return treeList;
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){                   //分页查询接口
        return Result.success(categoryService.page(categoryPageRequest));    //返回的是对象
    }
}
