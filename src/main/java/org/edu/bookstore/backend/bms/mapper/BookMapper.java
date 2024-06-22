package org.edu.bookstore.backend.bms.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.bms.entity.Book;
import org.edu.bookstore.backend.bms.vo.BookVO;

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
    List<Book> search(Page<Book> page, @Param("query") String query);

    Book checkISBN(@Param("isbn") String isbn);

    List<BookVO> getBookVOPage(IPage<BookVO> page);

    /**
     * 支持按作者姓名、图书isbn/图书名/出版社、分类名实现模糊搜索
     *
     * @param page  mybatis-plus分页对象
     * @param query 搜素关键字
     */
    List<BookVO> searchBookVOPage(IPage<BookVO> page, @Param("query") String query);

    Book selectById(@Param("book_id") Long bookID);
}
