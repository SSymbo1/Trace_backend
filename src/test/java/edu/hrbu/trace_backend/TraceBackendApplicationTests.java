package edu.hrbu.trace_backend;

import cn.hutool.core.lang.ObjectId;
import edu.hrbu.trace_backend.entity.enums.Secret;
import edu.hrbu.trace_backend.util.AesUtil;
import edu.hrbu.trace_backend.util.JwtUtil;
import edu.hrbu.trace_backend.util.QRCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Slf4j
@SpringBootTest
class TraceBackendApplicationTests {

    @Test
    void securityPasswordTest() {
        log.info("加密前{}", "111qwea1Q1q121111.");
        String security = AesUtil.encryptHex("111qwea1Q1q121111.");
        log.info("加密后{}", security);
        log.info("加密后长度{}", security.length());
        log.info("解密后{}", AesUtil.decryptStr(security));
    }

    @Test
    void jwtBuild() {
        log.info(JwtUtil.createJWT("1", 3600000));
    }

    @Test
    void testEnum() {
        log.info(Secret.AES.getValue());
    }

    @Test
    @SneakyThrows
    void qrCodeTest() {
        BufferedImage qrCode = (BufferedImage) QRCodeUtil.generateQRCode("www.baidu.com").getData().get("img");
        ImageIO.write(qrCode, "png", new java.io.File("D:/qrcode.png"));
    }

    @Test
    void traceIdTest(){
        log.info("trace-{}", ObjectId.next());
    }


}
