package edu.hrbu.trace_backend.global;

import edu.hrbu.trace_backend.entity.OnlineContext;
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
public class JwtTokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("token");
        try {
            log.info("jwt校验:{}",token);
            String user = JwtUtil.parseJWT(token).getSubject();
            log.info("登录用户为:{}", user);
            OnlineContext.setCurrent(user);
            return true;
        } catch (Exception exception) {
            if (exception instanceof ExpiredJwtException) {
                log.info("token已过期");
                response.setStatus(402);
            } else if (exception instanceof IllegalArgumentException || exception instanceof MalformedJwtException || exception instanceof io.jsonwebtoken.SignatureException) {
                log.info("token不合法");
                response.setStatus(401);
            }
            return false;
        }
    }
}
