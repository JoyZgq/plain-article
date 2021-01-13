package com.zgq.plainarticle.enums;

/**
 * 返回码
 */
public enum CodeEnum {
    /**
     * 失败
     */
    FAILED(0, "操作失败"),
    /**
     * 成功
     */
    SUCCESS(1, "执行成功"),
    SIGN_ERROR(100,"签名不正确")
    ;

    private final long code;
    private final String msg;

    CodeEnum(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CodeEnum fromCode(long code) {
        CodeEnum[] ecs = CodeEnum.values();
        for (CodeEnum ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }

    public long getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return String.format(" ErrorCode:{code=%s, msg=%s} ", code, msg);
    }
}

