package edu.hrbu.trace_backend_job.config;

import edu.hrbu.trace_backend_job.util.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class ResourcesConfig implements WebMvcConfigurer {

    private final String BASE_PATH = FileUtil.getSystemPath();

    @Override
    @SneakyThrows
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatar/**")
                .addResourceLocations("file:///" + BASE_PATH + "avatar/");
        log.info("用户头像资源映射路径拦截器启动,路径:file:///{}avatar/", BASE_PATH);

        registry.addResourceHandler("/goods/**")
                .addResourceLocations("file:///" + BASE_PATH + "goods/");
        log.info("商品图像资源映射路径拦截器启动,路径:file:///{}goods/",BASE_PATH);
    }
}
