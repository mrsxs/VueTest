package com.song.service.lmpl;

import com.song.mapper.BookMapper;
import com.song.pojo.Book;
import com.song.pojo.PageBean;
import com.song.service.BookService;
import com.song.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author Administrator
 */
public class BookServicelmpl implements BookService {


    //获取sqlSessionFactory
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

/**
 * 查询所有图书
 * @return 图书集合
 */
    @Override
    public List<Book> selectAll() {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //调用方法
        List<Book> books = mapper.selectAll();
        //关闭sqlSession
        sqlSession.close();
        //  返回数据
        return books;
    }

    /**
     * 根据id查询图书   
     */

    @Override
    public void add(Book book) {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //调用方法
        mapper.add(book);
        //提交事务
        sqlSession.commit(); 
        //关闭sqlSession
        sqlSession.close();

    }

    /**
     * 根据id查询图书
     */

    @Override
    public void deleteByIds(int[] ids) {
        //获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //获取mapper
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //调用方法
        mapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //关闭sqlSession
        sqlSession.close();
    }

    /**
     * 分页条件查询
     *
     * @param currentPage 当前页
     * @param pageSize    每页显示的条数
     * @return
     */
    @Override
    public PageBean<Book> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //计算开始的索引
        int begin = (currentPage - 1) * pageSize;
        //查询的条目数
        int size = pageSize;
        //查询当前页数据
        List<Book> books = mapper.selectByPage(begin, size);
        //查询总条数
        int totalCount = mapper.selectTotalCount();
        //封装pageBean
        PageBean<Book> pageBean = new PageBean<>(totalCount, books);
        //关闭sqlSession
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Book> selectByPageCondition(int currentPage, int pageSize, Book book) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        BookMapper mapper = sqlSession.getMapper(BookMapper.class);
        //计算开始的索引
        int begin = (currentPage - 1) * pageSize;
        //查询的条目数
        int size = pageSize;
        //处理模糊查询条件
        if (book.getBookName() != null && !"".equals(book.getBookName())) {
            book.setBookName("%" + book.getBookName() + "%");
        }
        if (book.getAuthor() != null && !"".equals(book.getAuthor())) {
            book.setAuthor("%" + book.getAuthor() + "%");
        }
        if (book.getPublisher() != null && !"".equals(book.getPublisher())) {
            book.setPublisher("%" + book.getPublisher() + "%");
        }
        //查询当前页数据
        List<Book> books = mapper.selectByPageCondition(begin, size, book);

        //查询总条数
        int totalCount = mapper.selectTotalCountByCondition(book);
        PageBean<Book> pageBean = new PageBean<>(totalCount, books);
        sqlSession.close();

        return pageBean;
    }


}
