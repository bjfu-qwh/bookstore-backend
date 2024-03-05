package org.edu.bookstore.backend.business.author.service;

import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.business.author.entity.AuthorInfo;
import org.edu.bookstore.backend.business.author.mapper.BackendAuthorMapper;
import org.edu.bookstore.backend.dto.ResultDTO;
import org.edu.bookstore.backend.util.ResultDTOUtil;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BackAuthorService {
    private final BackendAuthorMapper backendAuthorMapper;

    public BackAuthorService(BackendAuthorMapper backendAuthorMapper) {
        this.backendAuthorMapper = backendAuthorMapper;
    }

    /**
     * 添加一位作者信息
     *
     * @param authorInfo 新增作者信息
     */
    public ResultDTO<String> addAuthor(AuthorInfo authorInfo) {
        int count = backendAuthorMapper.addAuthor(authorInfo);
        if (count != 0) {
            log.info("添加了一名作者.");
            return ResultDTOUtil.successWithMessageOnly(String.format("成功添加了%d个作者", count));
        }
        return ResultDTOUtil.error("服务器故障");
    }
}
