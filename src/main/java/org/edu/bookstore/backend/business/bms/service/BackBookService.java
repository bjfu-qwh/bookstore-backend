package org.edu.bookstore.backend.business.bms.service;

import org.edu.bookstore.backend.business.author.mapper.BackAuthorWorkMapper;
import org.edu.bookstore.backend.business.bms.entity.Book;
import org.edu.bookstore.backend.business.bms.mapper.BookMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;

import static org.edu.bookstore.backend.util.UUIDUtil.getUUID;

@Service
public class BackBookService {
    private final BookMapper bookMapper;

    private final BackAuthorWorkMapper authorWorkMapper;

    public BackBookService(BookMapper bookMapper, BackAuthorWorkMapper authorWorkMapper) {
        this.bookMapper = bookMapper;
        this.authorWorkMapper = authorWorkMapper;
    }

    /**
     * 商家工作人员添加一本图书。假设已经校验身份
     * <br>
     * 多客户端并发请求时，由于图书表的主键由数据库自增，数据库会处理并发请求
     *
     * @param book 需要添加的图书信息
     * @return 添加结果
     */
    public JSONResult<String> addBook(Book book) {
        synchronized (bookMapper) {
            if (bookMapper.checkISBN(book.getIsbn()) != null) {
                return JSONResultUtil.errorForbidden(String.format("ISBN %s 已被使用", book.getIsbn()));
            }
        }
        String uuid = getUUID();
        book.setId(uuid);
        int result = bookMapper.addBook(book);
        if (result != 0) {
            for (long authorID : book.getAuthors()) {
                authorWorkMapper.addAuthorWork(authorID, uuid);
            }
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


}
