package edu.hrbu.trace_backend.token;

import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TokenTest {

    /***
     * 测试生成token
     */
    @Test
    public void jwtBuildTest() {
        log.info(JwtUtil.createJWT("1", 3600000));
    }

}
