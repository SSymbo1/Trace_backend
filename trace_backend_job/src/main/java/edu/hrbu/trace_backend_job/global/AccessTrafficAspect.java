package edu.hrbu.trace_backend_job.global;

import edu.hrbu.trace_backend_business.annotation.AntiResubmit;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.annotation.TrafficLimit;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.enums.RedisKey;
import edu.hrbu.trace_backend_business.entity.enums.Statue;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Slf4j
@Aspect
@Component
//  接口访问控制切面
public class AccessTrafficAspect {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    //  单一ip地址访问接口流量控制
    @SneakyThrows
    @Around("@annotation(edu.hrbu.trace_backend_business.annotation.TrafficLimit)")
    public Object interfaceAccessTrafficControl(ProceedingJoinPoint point) {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        TrafficLimit trafficLimit = ((MethodSignature) point.getSignature())
                .getMethod()
                .getAnnotation(TrafficLimit.class);
        String requestAddress = Objects.requireNonNull(attrs).getRequest().getRemoteAddr();
        log.info("from:{},请求方法:{}", requestAddress, ((MethodSignature) point.getSignature()).getMethod());
        String key = RedisKey.TRAFFIC.getValue() + requestAddress;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if (cache == null) {
            stringRedisTemplate.opsForValue().set(key, "1");
            stringRedisTemplate.expire(key, trafficLimit.times(), trafficLimit.timeUnit());
            return point.proceed();
        }
        Long ttl = stringRedisTemplate.getExpire(key);
        if (Integer.parseInt(cache) <= trafficLimit.maxRequest() && ttl != null) {
            cache = String.valueOf(Integer.parseInt(cache) + 1);
            stringRedisTemplate.opsForValue().set(key, cache);
            stringRedisTemplate.expire(key, ttl, trafficLimit.timeUnit());
            return point.proceed();
        } else {
            log.warn("from:{},访问服务频率过高暂时阻止访问", requestAddress);
            stringRedisTemplate.expire(key, trafficLimit.punish(), trafficLimit.timeUnit());
            return Result.custom(
                    Message.TOO_FAST.getValue(),
                    Statue.FREQUENCY.getValue(),
                    true
            );
        }
    }

    //  阻止短时间内重复提交
    @SneakyThrows
    @Around("@annotation(edu.hrbu.trace_backend_business.annotation.AntiResubmit)")
    public Object interfaceAntiResubmit(ProceedingJoinPoint point) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes())).getRequest();
        AntiResubmit antiResubmit = ((MethodSignature) point.getSignature())
                .getMethod()
                .getAnnotation(AntiResubmit.class);
        String accountID = JwtUtil.parseJWT(request.getHeader("token")).getSubject();
        String key = point.getSignature().getName() + ":" + accountID;
        String cache = stringRedisTemplate.opsForValue().get(key);
        if (cache == null) {
            stringRedisTemplate.opsForValue().set(key, "exist");
            stringRedisTemplate.expire(key, antiResubmit.times(), antiResubmit.timeUnit());
            return point.proceed();
        } else {
            log.warn("from:{},存在短时间内重复提交暂时阻止提交", request.getRemoteAddr());
            stringRedisTemplate.expire(key, antiResubmit.punish(), antiResubmit.timeUnit());
            return Result.custom(
                    Message.TOO_FAST.getValue(),
                    Statue.FREQUENCY.getValue(),
                    true
            );
        }
    }

    //  接口防抖
    @SneakyThrows
    @Around("@annotation(edu.hrbu.trace_backend_business.annotation.AntiShake)")
    public Object interfaceAntiShake(ProceedingJoinPoint point) {
        return point.proceed();
    }

}
