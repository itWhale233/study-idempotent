package me.zxxj.studyidempotent.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 业务异常
 *
 * @author whale
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceException extends RuntimeException {
    private String msg;
    private Integer status;

    public ServiceException(String msg) {
        this.msg = msg;
    }
}
