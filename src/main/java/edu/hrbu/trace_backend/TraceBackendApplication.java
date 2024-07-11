package edu.hrbu.trace_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("edu.hrbu.trace_backend.mapper")
@SpringBootApplication
public class TraceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceBackendApplication.class, args);
    }

}
