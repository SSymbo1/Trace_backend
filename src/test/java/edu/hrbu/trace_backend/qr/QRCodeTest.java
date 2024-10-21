package edu.hrbu.trace_backend.qr;

import edu.hrbu.trace_backend.util.QRCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

@Slf4j
@SpringBootTest
public class QRCodeTest {

    /***
     * 测试生成二维码
     */
    @Test
    @SneakyThrows
    public void generateQRCodeTest() {
        BufferedImage qrCode = (BufferedImage) QRCodeUtil.generateQRCode("www.baidu.com").getData().get("img");
        ImageIO.write(qrCode, "png", new java.io.File("D:/qrcode.png"));
    }

}
