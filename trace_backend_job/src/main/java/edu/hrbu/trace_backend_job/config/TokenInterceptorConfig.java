package edu.hrbu.trace_backend_job.config;

import edu.hrbu.trace_backend_job.global.TokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Slf4j
@Component
public class TokenInterceptorConfig implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("token校验拦截器启动");
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/segment/**/**/**")
                .addPathPatterns("/monitor/**/**/**")
                .addPathPatterns("/analysis/**/**/**")
                .addPathPatterns("/subject/**/**/**")
                .addPathPatterns("/file/**/**/**")
                .addPathPatterns("/menue/**/**/**")
                .addPathPatterns("/common/**/**/**")
                .addPathPatterns("/system/**/**/**");
    }
}
