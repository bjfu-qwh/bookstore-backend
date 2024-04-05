package org.edu.bookstore.backend.business.bms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.edu.bookstore.backend.business.author.mapper.BackAuthorWorkMapper;
import org.edu.bookstore.backend.business.bms.dto.NewBookDTO;
import org.edu.bookstore.backend.business.bms.entity.Book;
import org.edu.bookstore.backend.business.bms.mapper.BookMapper;
import org.edu.bookstore.backend.business.bms.vo.BookVO;
import org.edu.bookstore.backend.business.category.mapper.CategoryBookMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.dto.PagedDTO;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.edu.bookstore.backend.util.UUIDUtil.getUUID;

@Service
public class BackBookService {
    private final BookMapper bookMapper;

    private final BackAuthorWorkMapper authorWorkMapper;

    private final CategoryBookMapper categoryBookMapper;

    public BackBookService(BookMapper bookMapper,
                           BackAuthorWorkMapper authorWorkMapper, CategoryBookMapper categoryBookMapper) {
        this.bookMapper = bookMapper;
        this.authorWorkMapper = authorWorkMapper;
        this.categoryBookMapper = categoryBookMapper;
    }

    /**
     * 商家工作人员添加一本图书。假设已经校验身份
     * <br>
     * 多客户端并发请求时，由于图书表的主键由数据库自增，数据库会处理并发请求
     *
     * @param bookDTO 需要添加的图书信息
     * @return 添加结果
     */
    @Transactional
    public JSONResult<String> addBook(NewBookDTO bookDTO) {
        synchronized (bookMapper) {
            if (bookMapper.checkISBN(bookDTO.getIsbn()) != null) {
                return JSONResultUtil.errorForbidden(String.format("ISBN %s 已被使用", bookDTO.getIsbn()));
            }
        }
        String uuid = getUUID();
        Book book = newBookToBook(bookDTO, uuid);
        int result = bookMapper.addBook(book);
        if (result != 0) {
            for (long authorID : bookDTO.getAuthors()) {
                authorWorkMapper.addAuthorWork(authorID, uuid);
            }
            categoryBookMapper.addPath(uuid, bookDTO.getCategories());
            return JSONResultUtil.successWithMessageOnly(String.format("成功添加了%d本图书", result));
        }
        return JSONResultUtil.error("服务器故障");
    }

    public JSONResult<Boolean> checkISBN(String isbn) {
        boolean exists = false;
        synchronized (bookMapper) {
            if (bookMapper.checkISBN(isbn) != null) {
                exists = true;
            }
        }
        return JSONResultUtil.successWithDataOnly(exists);
    }

    public JSONResult<PagedDTO<BookVO>> getBookVOPage(int pageID, int pageSize) {
        Page<BookVO> page = new Page<>(pageID, pageSize);
        List<BookVO> pages = bookMapper.getBookVOPage(page);
        return JSONResultUtil.successWithDataOnly(
                new PagedDTO<>(page.getTotal(), pageID, pageSize, pages)
        );
    }

    public JSONResult<PagedDTO<BookVO>> searchBookVOPage(int pageID, int pageSize, String query) {
        Page<BookVO> page = new Page<>(pageID, pageSize);
        List<BookVO> pages = bookMapper.searchBookVOPage(page, query);
        return JSONResultUtil.successWithDataOnly(
                new PagedDTO<>(page.getTotal(), pageID, pageSize, pages)
        );
    }

    private Book newBookToBook(NewBookDTO bookDTO, String id) {
        Book book = new Book();
        book.setId(id);
        book.setPrice(bookDTO.getPrice());
        book.setBookName(bookDTO.getBookName());
        book.setAmount(bookDTO.getAmount());
        book.setPage(bookDTO.getPage());
        book.setType(bookDTO.getType());
        book.setIsbn(bookDTO.getIsbn());
        book.setPress(bookDTO.getPress());
        book.setPublished(bookDTO.getPublished());
        book.setEdition(bookDTO.getEdition());
        book.setUrl(bookDTO.getUrl());
        return book;
    }

}
