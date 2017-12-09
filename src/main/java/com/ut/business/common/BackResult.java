package com.ut.business.common;

import org.springframework.data.domain.Page;

/**
 * 返回对象
 */
public class BackResult<T> {

    //返回代码（1：成功，2后台异常，3：密码或账号错误）
    private int code = 1;

    //返回消息(默认成功)
    private String msg = "";

    //返回对象
    private T result;

    public BackResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
