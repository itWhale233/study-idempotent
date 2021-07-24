package me.zxxj.studyidempotent.service.impl;

import me.zxxj.studyidempotent.basic.R;
import me.zxxj.studyidempotent.basic.RCode;
import me.zxxj.studyidempotent.exception.ServiceException;
import me.zxxj.studyidempotent.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Token Service Implement
 *
 * @author whale
 */
@Service
public class TokenServiceImpl implements TokenService {

    // token 标识
    private static final String TOKEN_FLAG = "token";

    // token 格式
    private static final String TOKEN_PREFIX = "idempotent:token:%s";

    // token 过期时间
    private static final Integer TOKEN_EXPIRATION_TIME = 60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 生成 Token
     *
     * @return
     */
    @Override
    public R createToken() {
        // 使用 UUID 生成 Token
        String token = UUID.randomUUID().toString();
        String zone = String.format(TOKEN_PREFIX, token);

        // 存入 redis
        redisTemplate.opsForValue().set(zone, "0", TOKEN_EXPIRATION_TIME, TimeUnit.SECONDS);

        return R.success(RCode.SUCCESS.getMsg(), token);
    }

    /**
     * 校验 Token
     *
     * @param request
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        // 获取判断 header 中的 token
        String token = request.getHeader(TOKEN_FLAG);

        // header 中不存在尝试在 参数中获取
        if (!StringUtils.hasLength(token)) {
            token = request.getParameter(TOKEN_FLAG);
            if (!StringUtils.hasLength(token)) {
                throw new ServiceException(RCode.ILLEGAL_ARGUMENT.getMsg());
            }
        }

        String zone = String.format(TOKEN_PREFIX, token);
        // 判断 token 是否在 redis 中
        if (!redisTemplate.hasKey(zone)) {
            throw new ServiceException(RCode.REPETITIVE_OPERATION.getMsg());
        }


        // 删除 token
        Boolean delete = redisTemplate.delete(zone);

        // 如果删除失败，说明已经被其他线程删除了
        if (!delete) {
            throw new ServiceException(RCode.REPETITIVE_OPERATION.getMsg());
        }
    }
}
