package com.example.springboot.service;

import com.example.springboot.controller.request.AdminPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> list();     //返回所有数据的方法(接口)    未实现-->impl里实现类

    Object page(CategoryPageRequest categoryPageRequest);

    void save(Category category);

    Category getById(Integer id);

    void update(Category category);

    void deleteById(Integer id);
}
