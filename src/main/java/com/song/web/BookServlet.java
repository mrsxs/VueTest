package com.song.web;

import com.alibaba.fastjson.JSON;
import com.song.pojo.Book;
import com.song.pojo.PageBean;
import com.song.service.BookService;
import com.song.service.lmpl.BookServicelmpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * 替换httpServlet 根据业务需求
 *
 * @author Administrator
 */

@WebServlet("/book/*")
public class BookServlet extends BaseServlet {
    BookService bookService = new BookServicelmpl();

    /**
     * 查询所有图书
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */ 

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //查询所有
        response.setHeader("Access-Control-Allow-Origin", "http://127.0.0.1:5500");
        List<Book> books = bookService.selectAll();
        request.setAttribute("books", books);

        String jsonString = JSON.toJSONString(books);
        response.setContentType("text/JSON;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 添加图书
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //1,获取json
        BufferedReader reader = request.getReader();
        String params = reader.readLine();

        //2,转换成对象
        Book book = JSON.parseObject(params, Book.class);

        //3,调用service
        bookService.add(book);
        //4,响应
        response.getWriter().write("success");
    }
    /**
     * 批量删除
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1,获取json
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //2,转换成对象
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println(ids);
        //3,调用service
        bookService.deleteByIds(ids);
        //4,响应
        response.getWriter().write("success");
    }
   /**
     * 分页查询
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException 异常
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //接收参数
        String currentPage = request.getParameter("currentPage"); //当前页
        String pageSize = request.getParameter("pageSize");//每页显示的条数
        //调用service
        PageBean<Book> pageBean = bookService.selectByPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize));
        //响应
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/JSON;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     *  分页条件查询
     * @param request 请求
     * @param response 响应
     * @throws ServletException 异常
     * @throws IOException
     */
    public void selectByPageCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //post乱码
        request.setCharacterEncoding("UTF-8");
        // Set the character encoding for response
        response.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String params = reader.readLine();
        //转换成对象
        Book book = JSON.parseObject(params, Book.class);
        //获取post当前页和每页显示的条数
        String currentPage = request.getParameter("currentPage"); //当前页
        String pageSize = request.getParameter("pageSize");//每页显示的条数
        //调用service
        PageBean<Book> pageBean = bookService.selectByPageCondition(Integer.parseInt(currentPage), Integer.parseInt(pageSize), book);

        //响应
        String jsonString = JSON.toJSONString(pageBean);
        response.setContentType("text/JSON;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
