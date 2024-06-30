package com.nbb.common.core.web.domain;

import cn.hutool.http.HttpStatus;

import java.io.Serializable;

/**
 * 响应信息主体
 * 服务内部之间调用返回RestResult
 */
public class RestResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = HttpStatus.HTTP_OK;

    /** 失败 */
    public static final int FAIL = HttpStatus.HTTP_INTERNAL_ERROR;

    private int code;

    private String msg;

    private T data;

    public static <T> RestResult<T> ok()
    {
        return restResult(null, SUCCESS, null);
    }

    public static <T> RestResult<T> ok(T data)
    {
        return restResult(data, SUCCESS, null);
    }

    public static <T> RestResult<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> RestResult<T> fail()
    {
        return restResult(null, FAIL, null);
    }

    public static <T> RestResult<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> RestResult<T> fail(T data)
    {
        return restResult(data, FAIL, null);
    }

    public static <T> RestResult<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> RestResult<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    private static <T> RestResult<T> restResult(T data, int code, String msg)
    {
        RestResult<T> apiResult = new RestResult<>();
        apiResult.setCode(code);
        apiResult.setData(data);
        apiResult.setMsg(msg);
        return apiResult;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getData()
    {
        return data;
    }

    public void setData(T data)
    {
        this.data = data;
    }

    public static <T> Boolean isError(RestResult<T> ret)
    {
        return !isSuccess(ret);
    }

    public static <T> Boolean isSuccess(RestResult<T> ret)
    {
        return RestResult.SUCCESS == ret.getCode();
    }
}
