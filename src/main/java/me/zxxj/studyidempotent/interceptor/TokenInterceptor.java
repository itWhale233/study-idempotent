package me.zxxj.studyidempotent.interceptor;

import me.zxxj.studyidempotent.annotations.ApiIdempotent;
import me.zxxj.studyidempotent.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Token Interceptor
 *
 * @author whale
 */

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 只拦截方法
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 判断方法上是否有 ApiIdempotent 注解
        ApiIdempotent annotation = handlerMethod.getMethod().getAnnotation(ApiIdempotent.class);
        if (annotation != null) {
            tokenService.checkToken(request);
        }

        return true;
    }
}
