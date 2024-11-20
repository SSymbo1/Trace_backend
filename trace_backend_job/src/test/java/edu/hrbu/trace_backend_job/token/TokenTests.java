package edu.hrbu.trace_backend_job.token;

import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class TokenTests {

    @Test
    public void generateTimeToken() {
        log.info(JwtUtil.createJWT("1", 360000000000L));
    }

}
