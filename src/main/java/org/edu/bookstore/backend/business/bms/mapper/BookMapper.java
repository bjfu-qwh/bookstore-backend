package org.edu.bookstore.backend.business.bms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.business.bms.entity.Book;

import java.util.List;

public interface BookMapper {
    /**
     * 添加图书记录
     *
     * @param book 需要添加的图书实体
     */
    int addBook(@Param("book") Book book);

    /**
     * 按照图书名称/出版社/作家进行模糊搜索，或者根据ISBN进行明确搜索。
     *
     * @param page  分页对象，通过这个接口指定
     * @param query 查询关键字
     * @return {@link Book}对象
     */
    List<Book> searchByBookName(Page<Book> page, @Param("query") String query);

    Book getByID(@Param("id") long bookID);
}
