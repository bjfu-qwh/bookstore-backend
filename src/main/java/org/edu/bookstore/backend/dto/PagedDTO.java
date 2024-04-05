package org.edu.bookstore.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedDTO<T> implements Serializable {
    /**
     * total表示原表的记录总数量
     */
    @JsonProperty(value = "total")
    private Long total;

    /**
     * pageID表示页码，默认值是1
     */
    @JsonProperty(value = "pageID")
    private int pageID;

    /**
     * pageCount表示一次分页请求的词条数量
     */
    @JsonProperty(value = "pageSize")
    private int pageSize;

    /**
     * 这里已经默认数据用List集合
     */
    @JsonProperty(value = "data")
    private List<T> data;
}
