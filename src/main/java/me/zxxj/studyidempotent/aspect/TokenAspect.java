package me.zxxj.studyidempotent.aspect;

import lombok.extern.slf4j.Slf4j;
import me.zxxj.studyidempotent.service.TokenService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Token Aspect
 *
 * @author whale
 */
@Aspect
@Component
@Slf4j
public class TokenAspect {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenService tokenService;

    @Pointcut("execution(public * me.zxxj.studyidempotent.controller.TestController.*(..)) && @annotation(me.zxxj.studyidempotent.annotations.ApiIdem)")
    public void tokenAdvice() {
    }

    @Before("tokenAdvice()")
    public void tokenCheck(JoinPoint joinPoint) {
        // 使用切面获取 HttpServletRequest
        // HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        tokenService.checkToken(request);
    }

}
