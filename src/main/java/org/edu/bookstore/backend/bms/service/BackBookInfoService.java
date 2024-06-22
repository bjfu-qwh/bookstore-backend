package org.edu.bookstore.backend.bms.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.bms.mapper.BookInfoMapper;
import org.edu.bookstore.backend.bms.entity.Book;
import org.edu.bookstore.backend.bms.mapper.BookMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;

/**
 * 有关图书信息的业务层。这个类提供后台工作人员的服务，涉及较多同步问题。
 */
@Service
@Slf4j
public class BackBookInfoService {
    private final BookInfoMapper infoMapper;

    private final BookMapper bookMapper;

    public BackBookInfoService(BookInfoMapper infoMapper, BookMapper bookMapper) {
        this.infoMapper = infoMapper;
        this.bookMapper = bookMapper;
    }

    /**
     * 修改图书名称。
     *
     * @param bookID   图书编号
     * @param bookName 新书名称
     * @param workerID 修改工作人员账号ID
     * @return 修改结果
     */
    public JSONResult<String> changeBookName(long bookID, String bookName, String workerID) {
        Book book = bookMapper.selectById(bookID);
        if (book == null) {
            return JSONResultUtil.errorNotFound("未能查询到相关图书，请稍后再试");
        }
        log.info("工作账号{}修改了图书:{}的名称", workerID, bookID);
        infoMapper.changeBookName(bookID, bookName);
        return JSONResultUtil.successWithMessageOnly("修改成功");
    }
}
