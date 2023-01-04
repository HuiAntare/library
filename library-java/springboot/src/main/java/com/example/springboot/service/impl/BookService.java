package com.example.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.example.springboot.controller.request.BookPageRequest;
import com.example.springboot.entity.Book;
import com.example.springboot.mapper.BookMapper;
import com.example.springboot.service.IBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BookService implements IBookService {

    @Autowired       //导入categoryMapper,
    BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public Object page(BookPageRequest bookPageRequest) {
        PageHelper.startPage(bookPageRequest.getPageNum(),bookPageRequest.getPageSize());  //插件处理数量和页数
        List<Book> books = bookMapper.listByCondition(bookPageRequest);//条件查询
        return new PageInfo<>(books);
    }

    @Override
    public void save(Book book) {
        book.setCategory(category(book.getCategories()));
        bookMapper.save(book);
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    @Override
    public void update(Book book) {
        book.setCategory(category(book.getCategories()));
        book.setUpdatetime(LocalDate.now());
        bookMapper.updateById(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }

    private String category(List<String> categories){
        StringBuilder sb = new StringBuilder();
        if(CollUtil.isNotEmpty(categories)){
            categories.forEach(v -> sb.append(v).append(" > "));
            return sb.substring(0,sb.lastIndexOf(" > "));
        }
        return sb.toString();
    }
}
