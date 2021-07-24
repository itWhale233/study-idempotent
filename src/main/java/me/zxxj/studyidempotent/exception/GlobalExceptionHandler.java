package me.zxxj.studyidempotent.exception;

import lombok.extern.slf4j.Slf4j;
import me.zxxj.studyidempotent.basic.R;
import me.zxxj.studyidempotent.basic.RCode;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理
 *
 * @author whale
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public R exceptionHandler(ServiceException e) {
        return R.error(e.getMsg());
    }

    @ExceptionHandler(ServletRequestBindingException.class)
    @ResponseBody
    public R exceptionHandler(ServletRequestBindingException e) {
        return R.error(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R exceptionHandler(Exception e) {
        return R.error(RCode.SERVER_ERROR.getMsg());
    }

}
