package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.ResultDTO;

import static org.edu.bookstore.backend.constant.code.Code.*;

public class ResultDTOUtil {
    public static <T> ResultDTO<T> successWithMessageOnly(String message) {
        return new ResultDTO<>(CODE_SUCCESS, message, null);
    }

    public static <T> ResultDTO<T> successWithDataOnly(T data) {
        return new ResultDTO<>(CODE_SUCCESS, null, data);
    }

    public static <T> ResultDTO<T> success(String message, T data) {
        return new ResultDTO<>(CODE_SUCCESS, message, data);
    }

    public static <T> ResultDTO<T> errorNotFound(String message) {
        return new ResultDTO<>(CODE_NOT_FOUND, message, null);
    }

    public static <T> ResultDTO<T> errorForbidden(String message) {
        return new ResultDTO<>(CODE_FORBIDDEN, message, null);
    }

    public static <T> ResultDTO<T> errorUnAuthorized(String message) {
        return new ResultDTO<>(CODE_UNAUTHORIZED, message, null);
    }
}
