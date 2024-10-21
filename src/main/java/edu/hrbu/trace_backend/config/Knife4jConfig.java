package edu.hrbu.trace_backend.config;

import edu.hrbu.trace_backend.entity.enums.Knife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
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
        log.info(
                "启动knife4j接口文档,地址:http://{}:{}/doc.html",
                inetAddress.getHostAddress(),
                serverProperties.getPort()
        );
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title(Knife4j.TITLE.getValue())
                .contact(new Contact(
                        Knife4j.AUTHOR.getValue(),
                        Knife4j.GITHUB.getValue(),
                        Knife4j.EMAIL.getValue()
                ))
                .version(Knife4j.VERSION.getValue())
                .description(Knife4j.DESCRIPTION.getValue())
                .build();
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(Knife4j.GROUP.getValue())
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage(Knife4j.PACKAGE.getValue()))
                .paths(PathSelectors.any())
                .build();
    }
}
