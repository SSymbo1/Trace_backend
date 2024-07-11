package edu.hrbu.trace_backend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Configuration
@EnableSwagger2WebMvc
public class Knife4jConfig {
    @Resource
    private ServerProperties serverProperties;

    @Bean
    public Docket backendDocket() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        log.info("启动knife4j接口文档,地址:http://{}:{}/doc.html", inetAddress.getHostAddress(), serverProperties.getPort());
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("溯源-项目接口文档")
                .version("dev")
                .description("这是溯源系统项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("edu.hrbu.trace_backend.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}
