package org.edu.bookstore.backend.util;

import org.edu.bookstore.backend.dto.JSONResult;

import static org.edu.bookstore.backend.constant.code.Code.*;

public class JSONResultUtil {
    public static <T> JSONResult<T> successWithMessageOnly(String message) {
        return new JSONResult<>(CODE_SUCCESS, message, null);
    }

    public static <T> JSONResult<T> successWithDataOnly(T data) {
        return new JSONResult<>(CODE_SUCCESS, null, data);
    }

    public static <T> JSONResult<T> success(String message, T data) {
        return new JSONResult<>(CODE_SUCCESS, message, data);
    }

    public static <T> JSONResult<T> error(String message) {
        return new JSONResult<>(CODE_ERROR, message, null);
    }

    public static <T> JSONResult<T> errorNotFound(String message) {
        return new JSONResult<>(CODE_NOT_FOUND, message, null);
    }

    public static <T> JSONResult<T> errorForbidden(String message) {
        return new JSONResult<>(CODE_FORBIDDEN, message, null);
    }

    public static <T> JSONResult<T> errorUnAuthorized(String message) {
        return new JSONResult<>(CODE_UNAUTHORIZED, message, null);
    }
}
