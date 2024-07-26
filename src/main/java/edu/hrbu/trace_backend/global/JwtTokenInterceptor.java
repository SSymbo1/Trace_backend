package edu.hrbu.trace_backend.global;

import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.enums.Statue;
import edu.hrbu.trace_backend.util.JwtUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
//  JwtToken校验拦截器
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        log.info("jwt校验:{}", token);
        String user = JwtUtil.parseJWT(token).getSubject();
        log.info("调用接口用户的aid为:{}", user);
        OnlineContext.setCurrent(token);
        return true;
    }
}
