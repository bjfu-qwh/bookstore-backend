package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.PagedDTO;

import java.util.List;

public class PagedDTOUtil {
    public static <T> PagedDTO<T> create(long total, int pageID, int pageSize, List<T> data) {
        return new PagedDTO<>(total, pageID, pageSize, data);
    }
}
