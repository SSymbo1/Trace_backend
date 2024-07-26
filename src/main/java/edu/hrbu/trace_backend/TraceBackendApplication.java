package edu.hrbu.trace_backend;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("edu.hrbu.trace_backend.mapper")
@SpringBootApplication
public class TraceBackendApplication {

    public static void main(String[] args) {
        // 启动应用
        SpringApplication.run(TraceBackendApplication.class, args);
    }

}
