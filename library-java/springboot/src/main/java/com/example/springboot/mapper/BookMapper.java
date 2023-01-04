package com.example.springboot.mapper;

import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    List<Book> list();     //返回所有数据的方法(接口)

    List<Book> listByCondition(BookPageRequest bookPageRequest);

    void save(Book book);

    Book getById(Integer id);

    void updateById(Book book);

    void deleteById(Integer id);
    Book getByNo(String bookNo);
    void updateNumByNo(String bookNo);
}
