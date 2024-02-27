package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.PagedDTO;

import java.util.List;

public class PagedDTOUtil {
    public static <T> PagedDTO<T> create(int pageID, int pageSize, int total, List<T> data) {
        return new PagedDTO<>(pageID, pageSize, total, data);
    }
}
