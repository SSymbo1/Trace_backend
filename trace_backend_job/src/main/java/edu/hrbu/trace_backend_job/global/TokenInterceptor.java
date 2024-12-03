package edu.hrbu.trace_backend_job.global;

import edu.hrbu.trace_backend_business.entity.OnlineContext;
import edu.hrbu.trace_backend_business.entity.enums.Exception;
import edu.hrbu.trace_backend_business.entity.enums.RedisKey;
import edu.hrbu.trace_backend_business.entity.enums.Secret;
import edu.hrbu.trace_backend_business.global.exception.TokenExpireException;
import edu.hrbu.trace_backend_business.global.exception.TokenInconsistentException;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import io.micrometer.core.lang.NonNullApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@NonNullApi
//  Token校验拦截器,包括Token续租
public class TokenInterceptor implements HandlerInterceptor {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        String user = JwtUtil.parseJWT(token).getSubject();
        String key = RedisKey.USER.getValue() + user;
        String lockKey = RedisKey.RENEWAL.getValue() + user;
        String cache = stringRedisTemplate.opsForValue().get(key);
        Long expire = stringRedisTemplate.getExpire(key, TimeUnit.MILLISECONDS);
        String lock = stringRedisTemplate.opsForValue().get(lockKey);
        if (cache == null || expire == null) {
            throw new TokenExpireException(Exception.TOKEN_EXPIRED.getValue());
        }
        if (!Objects.equals(cache, token)) {
            throw new TokenInconsistentException(Exception.TOKEN_INCONSISTENT.getValue());
        }
        if (expire <= Long.parseLong(Secret.JWT_RENEWAL.getValue()) && lock == null) {
            stringRedisTemplate.expire(key, Long.parseLong(Secret.JWT_EXPIRE.getValue()), TimeUnit.MILLISECONDS);
            stringRedisTemplate.opsForValue().set(lockKey, "exist");
            stringRedisTemplate.expire(lockKey, Integer.parseInt(Secret.RENEWAL_LOCK.getValue()), TimeUnit.SECONDS);
            log.info("已续租aid为:{}的token", user);
        }
        log.info("调用接口用户的aid为:{}", user);
        OnlineContext.setCurrent(token);
        return true;
    }
}
