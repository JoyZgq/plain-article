package com.zgq.plainarticle.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zgq.plainarticle.enums.CodeEnum;
import lombok.Data;

/**
 * 响应信息返回
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Response<T> {
    private long code;
    private String msg;
    private T data;
    public Response() {
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getMsg();
    }

    public Response(T data) {
        this.data = data;
        this.code = CodeEnum.SUCCESS.getCode();
        this.msg = CodeEnum.SUCCESS.getMsg();
    }
    public Response(String msg) {
        this.code = CodeEnum.FAILED.getCode();
        this.msg = msg;
    }
    public Response(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public Response(long code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Response(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public Response(CodeEnum codeEnum, T data) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
        this.data = data;
    }

    public boolean ok() {
        return CodeEnum.SUCCESS.getCode() == code;
    }

    /**
     * 服务间调用非业务正常，异常直接释放
     */
    public T serviceData() {
        if (!ok()) {
            throw new RuntimeException(this.msg);
        }
        return data;
    }

}

