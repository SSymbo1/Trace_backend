package edu.hrbu.trace_backend.config;

import edu.hrbu.trace_backend.global.JwtTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Slf4j
@Component
public class JwtTokenInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("token校验拦截器启动");
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/file/**/**")
                .addPathPatterns("/menue/**/**")
                .addPathPatterns("/common/**/**")
                .addPathPatterns("/system/**/**")
                .excludePathPatterns("/welcome/**/**");
    }

}
