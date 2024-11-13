package edu.hrbu.trace_backend;

import edu.hrbu.trace_backend.strategy.impl.struct.DayStructOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.annotation.Resource;


@Slf4j
@SpringBootTest
class TraceBackendApplicationTests {

    @Resource
    private ApplicationContext applicationContext;


    @Test
    public void test() {
        log.info(applicationContext.getBeanNamesForType(DayStructOperation.class)[0]);
    }

}
