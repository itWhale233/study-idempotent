package me.zxxj.studyidempotent.service;

import me.zxxj.studyidempotent.basic.R;
import me.zxxj.studyidempotent.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

/**
 * Token Service Interface
 *
 * @author whale
 */
public interface TokenService {

    /**
     * 生成 Token
     *
     * @return
     */
    R createToken();

    /**
     * 校验 Token
     *
     * @param request
     */
    void checkToken(HttpServletRequest request) throws ServiceException;
}
