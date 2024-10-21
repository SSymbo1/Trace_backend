package edu.hrbu.trace_backend.trace;

import cn.hutool.core.lang.ObjectId;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TraceTest {

    /***
     * 测试生成追溯码
     */
    @Test
    public void generateTraceCodeTest() {
        log.info("trace-{}", ObjectId.next());
    }

}
