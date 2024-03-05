package org.edu.bookstore.backend.business.bms.service;

import org.edu.bookstore.backend.business.bms.entity.Book;
import org.edu.bookstore.backend.business.bms.mapper.BookMapper;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.ResultDTOUtil;
import org.springframework.stereotype.Service;

@Service
public class BackBookService {
    private final BookMapper bookMapper;

    public BackBookService(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    /**
     * 商家工作人员添加一本图书。假设已经校验身份
     * <br>
     * 多客户端并发请求时，由于图书表的主键由数据库自增，数据库会处理并发请求
     *
     * @param book 需要添加的图书信息
     * @return 添加结果
     */
    public ResultDTO<String> addBook(Book book) {
        int result = bookMapper.addBook(book);
        if (result != 0) {
            return ResultDTOUtil.successWithMessageOnly(String.format("成功添加了%d本图书", result));
        }
        return ResultDTOUtil.error("服务器故障");
    }


}
