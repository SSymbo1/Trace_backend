package edu.hrbu.trace_backend.aes;

import edu.hrbu.trace_backend.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class AESTest {

    /***
     * 测试AES加密，解密
     */
    @Test
    public void AESCodingTest() {
        String pass = "111qwea1Q1q121111.";
        log.info("加密前{}", pass);
        String security = AesUtil.encryptHex(pass);
        log.info("加密后{}", security);
        log.info("加密后长度{}", security.length());
        log.info("解密后{}", AesUtil.decryptStr(security));
    }

}
