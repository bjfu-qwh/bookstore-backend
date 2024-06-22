package org.edu.bookstore.backend.category.mapper;

import org.apache.ibatis.annotations.Param;
import org.edu.bookstore.backend.category.entity.CategoryInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public interface CategoryBookMapper {
    int add(@Param("book_id") String bookID, @Param("category_id") String categoryID,
            @Param("parent_id") String parentID);

    default void addPath(String bookID, List<String> path) {
        for (int index = 0; index < path.size(); ++index) {
            String parentID = "";
            if (index > 0) {
                parentID = path.get(index - 1);
            }
            add(bookID, path.get(index), parentID);
        }
    }

    int delete(@Param("book_id") String bookID, @Param("category_id") String categoryID);

    int deleteAllChildren(@Param("parent_id") String target, @Param("book_id") String bookID);

    CategoryInfo getByBookIDAndCategoryID(@Param("book_id") String bookID,
                                          @Param("category_id") String categoryID);

    CategoryInfo getRootCategory(@Param("book_id") String bookID);

    CategoryInfo getChildByBookIDAndParentID(@Param("book_id") String bookID,
                                             @Param("parent_id") String parent);

    default List<CategoryInfo> getCategoryPath(String bookID) {
        ArrayList<CategoryInfo> categoryInfos = new ArrayList<>();
        CategoryInfo root = getRootCategory(bookID);
        if (root == null) {
            return new ArrayList<>();
        }
        categoryInfos.add(root);
        Stack<CategoryInfo> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            CategoryInfo current = stack.pop();
            CategoryInfo child = getChildByBookIDAndParentID(bookID, current.getId());
            if (child != null) {
                categoryInfos.add(child);
                stack.push(child);
            }
        }
        return categoryInfos;
    }
}
