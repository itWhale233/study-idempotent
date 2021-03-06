package me.zxxj.studyidempotent.basic;

import lombok.Getter;

/**
 * 返回值枚举
 *
 * @author whale
 */

@Getter
public enum RCode {

    SUCCESS(200, "操作成功"),
    ERROR(000, "操作失败"),
    SERVER_ERROR(500, "服务器异常"),

    ILLEGAL_ARGUMENT(10000, "参数不合法"),
    REPETITIVE_OPERATION(10001, "请勿重复操作"),
    ACCESS_LIMIT(10002, "请求太频繁, 请稍后再试");

    private Integer code;
    private String msg;

    RCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
