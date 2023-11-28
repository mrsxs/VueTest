package com.song.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Administrator
 */

public class BaseServlet extends HttpServlet {


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        //1.获取请求路径
        String uri = req.getRequestURI();
        //2获取最后的路径
       int index = uri.lastIndexOf("/");
        String substring = uri.substring(index + 1);
        //3.执行方法
        //3.1获取字节码对象
        Class<? extends BaseServlet> cls = this.getClass();
        //3.2获取方法对象
        try {
            Method method = cls.getMethod(substring, HttpServletRequest.class, HttpServletResponse.class);
            //3.3执行方法
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
