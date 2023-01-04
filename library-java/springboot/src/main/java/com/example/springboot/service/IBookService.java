package com.example.springboot.service;

import com.example.springboot.controller.request.BaseRequest;
import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.controller.request.CategoryPageRequest;
import com.example.springboot.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> list();     //返回所有数据的方法(接口)    未实现-->impl里实现类

    Object page(BookPageRequest bookPageRequest);

    void save(Book book);

    Book getById(Integer id);

    void update(Book book);

    void deleteById(Integer id);
}
