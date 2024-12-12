package com.cat.util;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class R extends HashMap<String, Object> {

    private static final int DEFAULT_SUCCESS_CODE = HttpStatus.SC_OK;
    private static final int DEFAULT_ERROR_CODE = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    private static final String DEFAULT_SUCCESS_MESSAGE = "success";
    private static final String DEFAULT_ERROR_MESSAGE = "未知异常，请联系管理员";

    public R() {
        put("code", DEFAULT_SUCCESS_CODE);
        put("msg", DEFAULT_SUCCESS_MESSAGE);
    }

    public static R ok() {
        return new R();
    }

    public static R ok(String msg) {
        return new R().put("msg", msg);
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R error(int code, String msg) {
        return new R().put("code", code).put("msg", msg);
    }

    public static R error(String msg) {
        return error(DEFAULT_ERROR_CODE, msg);
    }

    public static R error() {
        return error(DEFAULT_ERROR_MESSAGE);
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
