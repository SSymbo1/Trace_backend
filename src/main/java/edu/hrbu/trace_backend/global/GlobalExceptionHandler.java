package edu.hrbu.trace_backend.global;

import cn.hutool.crypto.CryptoException;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.enums.Statue;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Slf4j
@RestControllerAdvice
//  全局异常处理器
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    public Result tokenExpiredException(HttpServletResponse response, Exception exception) {
        log.error("token过期：{}", exception.getMessage());
        response.setStatus(Statue.EXPIRE_TOKEN.getValue());
        return Result.fail(Message.LOGIN_ERROR.getValue())
                .data(
                        "info",
                        Result.custom(
                                Message.EXPIRE_TOKEN.getValue(),
                                Statue.EXPIRE_TOKEN.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = {
            IllegalArgumentException.class,
            MalformedJwtException.class,
            io.jsonwebtoken.SignatureException.class}
    )
    public Result wrongTokenException(HttpServletResponse response, Exception exception) {
        log.error("token不合法：{}", exception.getMessage());
        response.setStatus(Statue.WRONG_TOKEN.getValue());
        return Result.fail(Message.LOGIN_ERROR.getValue())
                .data(
                        "info",
                        Result.custom(
                                Message.WRONG_TOKEN.getValue(),
                                Statue.WRONG_TOKEN.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = {
            IllegalBlockSizeException.class,
            BadPaddingException.class,
            InvalidKeyException.class,
            NoSuchAlgorithmException.class,
            CryptoException.class,
    })
    public Result wrongAesDecode(HttpServletResponse response, Exception exception) {
        log.error("AES解码失败：{}", exception.getMessage());
        response.setStatus(Statue.WRONG_AES.getValue());
        return Result.fail(Message.DECODE_WRONG.getValue())
                .data(
                        "info",
                        Result.custom(
                                Message.DECODE_WRONG.getValue(),
                                Statue.WRONG_AES.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = Exception.class)
    public Result unknownException(Exception exception) {
        log.error("服务器出现错误：{}", exception.getMessage());
        return Result.fail(Message.SERVER_ERROR.getValue()).data("code", Statue.FAIL.getValue());
    }
}
