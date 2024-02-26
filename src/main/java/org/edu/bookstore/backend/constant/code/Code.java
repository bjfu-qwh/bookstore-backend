package org.edu.bookstore.backend.constant.code;

/**
 * 这个类用于模拟HTTP状态码。
 */
public class Code {
    /**
     * 这是正常返回的代码
     */
    public static final int CODE_SUCCESS = 200;

    /**
     * 服务器错误的代码。一般不会想见到
     */
    public static final int CODE_ERROR = 500;

    /**
     * 表示找不到资源，若传到前端可以让它跳转
     */
    public static final int CODE_NOT_FOUND = 404;

    /**
     * 表示进行了禁止的操作
     */
    public static final int CODE_FORBIDDEN = 403;

    /**
     * 表示未认证，换言之很可能未登录
     */
    public static final int CODE_UNAUTHORIZED = 401;
}
