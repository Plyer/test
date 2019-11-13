package org.ljl.test.domain.component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lvjinglu
 * created at 2019/10/24
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private T data;
    private Integer code;
    private String msg;

    /**
     * 请求成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Response<T> success(T data) {
        return new Response(data, 200, null);
    }

    public static <T> Response<T> success(T data, String msg) {
        return new Response(data, 200, msg);
    }

    public static <T> Response<T> success() {
        return new Response(null, 200, null);
    }

    /**
     * 请求失败,程序错误
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Response<T> error(String msg) {
        return new Response<>(null, 500, msg);
    }

    public static <T> Response<T> error(){
        return new Response<>(null,500,"system error");
    }
    /**
     * 请求失败,需要展示给用户信息
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Response<T> fail(String msg) {
        return new Response<>(null, 206, msg);
    }

    public static Response build(int row) {
        if (row > 0) {
            return Response.success(null, "操作成功");
        } else {
            return Response.error("操作失败");
        }
    }
}
