package me.zxxj.studyidempotent.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 返回 Json 实体
 *
 * @author whale
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {

    private Integer status;

    private String msg;

    private Object data;


    public static R success() {
        return new R(RCode.SUCCESS.getCode(), null, null);
    }

    public static R success(String msg) {
        return new R(RCode.SUCCESS.getCode(), msg, null);
    }

    public static R success(Object data) {
        return new R(RCode.SUCCESS.getCode(), null, data);
    }

    public static R success(String msg, Object data) {
        return new R(RCode.SUCCESS.getCode(), msg, data);
    }

    public static R error(String msg) {
        return new R(RCode.ERROR.getCode(), msg, null);
    }

    public static R error(Object data) {
        return new R(RCode.ERROR.getCode(), null, data);
    }

    public static R error(String msg, Object data) {
        return new R(RCode.ERROR.getCode(), msg, data);
    }

}
