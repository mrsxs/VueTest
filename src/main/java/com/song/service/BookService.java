package com.song.service;

import com.song.pojo.Book;
import com.song.pojo.PageBean;

import java.util.List;

/**
 * @author Administrator
 */
public interface BookService {

    /**
     * 查询所有图书
     */
    List<Book> selectAll();
    /**
     * 添加图书
     */
    void add(Book book);
    /**
     * 批量删除
     * @param ids 图书id数组
     */
    void deleteByIds(int[] ids);

    /**
     * 分页查询
     * @param currentPage 当前页
     * @param pageSize 每页显示的条数
     * @return
     */
    PageBean<Book> selectByPage(int currentPage, int pageSize);

    /**
     * 分页条件查询
     * @param currentPage 当前页
     * @param pageSize 每页显示的条数
     * @param book 条件
     * @return
     */
    PageBean<Book> selectByPageCondition(int currentPage, int pageSize, Book book);

}
