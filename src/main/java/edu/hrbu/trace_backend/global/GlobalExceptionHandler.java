package edu.hrbu.trace_backend.global;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.crypto.CryptoException;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.enums.Statue;
import edu.hrbu.trace_backend.global.exception.ExcelException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelNullException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelStructException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTimeoutException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTypeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.servlet.http.HttpServletResponse;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
//  全局异常处理器
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ExpiredJwtException.class)
    public Result tokenExpiredException(HttpServletResponse response, Exception exception) {
        log.error("token过期：{}", exception.getMessage());
        response.setStatus(Statue.EXPIRE_TOKEN.getValue());
        return Result
                .fail(Message.LOGIN_ERROR.getValue())
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
            MalformedJwtException.class,
            io.jsonwebtoken.SignatureException.class
    })
    public Result wrongTokenException(HttpServletResponse response, Exception exception) {
        log.error("token不合法：{}", exception.getMessage());
        response.setStatus(Statue.WRONG_TOKEN.getValue());
        return Result
                .fail(Message.LOGIN_ERROR.getValue())
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
        return Result
                .fail(Message.DECODE_WRONG.getValue())
                .data(
                        "info",
                        Result.custom(
                                Message.DECODE_WRONG.getValue(),
                                Statue.WRONG_AES.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = {
            ExcelException.class,
            ExcelTypeException.class,
            ExcelStructException.class,
            ExcelNullException.class,
            ExcelTimeoutException.class
    })
    public Result excelException(HttpServletResponse response, Exception exception) {
        log.error("上传表格异常：{}", exception.getMessage());
        response.setStatus(Statue.TABLE_WRONG.getValue());
        return Result
                .fail(Message.TABLE_WRONG.getValue())
                .data(
                        "info",
                        Result.custom(
                                exception.getMessage(),
                                Statue.TABLE_WRONG.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result argumentNotValidException(HttpServletResponse response, MethodArgumentNotValidException exception) {
        log.error("参数校验异常：{}", Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        response.setStatus(Statue.WRONG_ARGUMENT.getValue());
        return Result
                .fail(Message.WRONG_ARGUMENT.getValue())
                .data(
                        "info",
                        Result.custom(
                                exception.getBindingResult().getFieldError().getDefaultMessage(),
                                Statue.WRONG_ARGUMENT.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = org.springframework.validation.BindException.class)
    public Result argumentBinkException(HttpServletResponse response, org.springframework.validation.BindException exception) {
        log.error("参数校验异常,存在参数为空：{}", Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
        response.setStatus(Statue.WRONG_ARGUMENT.getValue());
        return Result
                .fail(Message.WRONG_ARGUMENT.getValue())
                .data(
                        "info",
                        Result.custom(
                                exception.getBindingResult().getFieldError().getDefaultMessage(),
                                Statue.WRONG_ARGUMENT.getValue(),
                                false
                        )
                );
    }

    @ExceptionHandler(value = IORuntimeException.class)
    public Result IOException(Exception exception) {
        log.error("服务器读写操作错误：{}", exception.getMessage());
        log.error(exception.getMessage(), exception);
        return Result.fail(Message.SERVER_IO_ERROR.getValue()).data("code", Statue.FAIL.getValue());
    }

    @ExceptionHandler(value = Exception.class)
    public Result unknownException(Exception exception) {
        log.error("服务器出现错误：{}", exception.getMessage());
        log.error(exception.getMessage(), exception);
        return Result.fail(Message.SERVER_ERROR.getValue()).data("code", Statue.FAIL.getValue());
    }
}
