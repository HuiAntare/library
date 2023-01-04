package com.example.springboot.service.impl;

import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import com.example.springboot.mapper.CategoryMapper;
import com.example.springboot.service.ICategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BookService implements ICategoryService {

    @Autowired       //导入categoryMapper,
    CategoryMapper categoryMapper;

    @Override
    public List<Category> list() {
        return categoryMapper.list();
    }

    @Override
    public Object page(CategoryPageRequest categoryPageRequest) {
        PageHelper.startPage(categoryPageRequest.getPageNum(),categoryPageRequest.getPageSize());  //插件处理数量和页数
        //自关联查询
        List<Category> categories = categoryMapper.listByCondition(categoryPageRequest);//条件查询
        return new PageInfo<>(categories);
    }

    @Override
    public void save(Category category) {
        categoryMapper.save(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.getById(id);
    }

    @Override
    public void update(Category category) {
        category.setUpdatetime(LocalDate.now());
        categoryMapper.updateById(category);
    }

    @Override
    public void deleteById(Integer id) {
        categoryMapper.deleteById(id);
    }
}
