package com.example.springboot.mapper;

import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> list();     //返回所有数据的方法(接口)

    List<Category> listByCondition(CategoryPageRequest categoryPageRequest);

    void save(Category category);

    Category getById(Integer id);

    void updateById(Category category);

    void deleteById(Integer id);
}
