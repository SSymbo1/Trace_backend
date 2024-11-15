package edu.hrbu.trace_backend_business.util;

import cn.hutool.extra.qrcode.QrConfig;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;


@Slf4j
public class QRCodeUtil {

    @SneakyThrows
    public static Result generateQRCode(String url) {
        ClassPathResource resource = new ClassPathResource("/image/vite.png");
        InputStream inputStream = resource.getInputStream();
        BufferedImage logo = ImageIO.read(inputStream);
        BufferedImage qrcode = cn.hutool.extra.qrcode.QrCodeUtil.generate(
                url,
                QrConfig.create()
                        .setImg(logo)
                        .setWidth(300)
                        .setHeight(300)
        );
        log.info("已生成二维码");
        return Result.ok(Message.GENERATE_QR.getValue()).data("img", qrcode);
    }

}
