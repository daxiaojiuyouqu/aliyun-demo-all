package com.aliyun.aliyundemo.common;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyResult<T> {

    private T data;

    private int code;

    private String message;

    public ReplyResult(T data) {
        this.data = data;
        this.code = 200;
        this.message = "成功";
    }

    public ReplyResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReplyResult() {
        this.code = 200;
        this.message = "成功";
    }
}
