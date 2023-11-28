package com.song.mapper;

import com.song.pojo.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Administrator
 */
public interface BookMapper {

    //查询所有图书

    @Select("select * from books")
    @ResultMap("bookMap")
    List<Book> selectAll();

    //添加图书
    @Insert("insert into books values(null,#{bookName},#{author},#{publisher},#{price})")
    @ResultMap("bookMap")
    int add(Book book);

    //批量删除

    int deleteByIds(@Param("ids") int[] ids);


    /**
     * 查询总数
     * @return
     */
    @Select("select count(*) from books")
    int  selectTotalCount();
    /**
     * 分页查询
     * @param start
     * @param pageSize
     * @return
     */
    @Select("select * from books limit #{start},#{pageSize}")
    @ResultMap("bookMap")
    List<Book> selectByPage(@Param("start") int start,@Param("pageSize") int pageSize);

    /**
     * 分页条件查询
     */
    List<Book> selectByPageCondition(@Param("start") int start,@Param("pageSize") int pageSize,@Param("book") Book book);

    /**
     * 根据条件查询总数
     * @param book
     * @return
     */
    int selectTotalCountByCondition(Book book);
}
