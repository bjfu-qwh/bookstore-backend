package org.edu.bookstore.backend.author.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.edu.bookstore.backend.author.dto.AuthorSelectItemDTO;
import org.edu.bookstore.backend.author.entity.AuthorInfo;
import org.edu.bookstore.backend.author.mapper.BackendAuthorMapper;
import org.edu.bookstore.backend.dto.JSONResult;
import org.edu.bookstore.backend.util.JSONResultUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public JSONResult<String> addAuthor(AuthorInfo authorInfo) {
        int count = backendAuthorMapper.addAuthor(authorInfo);
        if (count != 0) {
            log.info("添加了一名作者.");
            return JSONResultUtil.successWithMessageOnly(String.format("成功添加了%d个作者", count));
        }
        return JSONResultUtil.error("服务器故障");
    }

    public JSONResult<List<AuthorSelectItemDTO>> allAuthorSelectorItem() {
        List<AuthorInfo> authors = backendAuthorMapper.selectList(new QueryWrapper<>());
        List<AuthorSelectItemDTO> authorSelectItemDTOList = new ArrayList<>();
        for (AuthorInfo author : authors) {
            authorSelectItemDTOList.add(authorToSelectorItem(author));
        }
        return JSONResultUtil.successWithDataOnly(authorSelectItemDTOList);
    }

    private AuthorSelectItemDTO authorToSelectorItem(AuthorInfo author) {
        return new AuthorSelectItemDTO(author.getId(), author.getName(), author.getNation());
    }
}
