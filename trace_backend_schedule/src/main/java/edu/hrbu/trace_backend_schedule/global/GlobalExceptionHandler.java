package edu.hrbu.trace_backend_schedule.global;

import cn.hutool.core.io.IORuntimeException;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.enums.Statue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
//  全局异常处理器
public class GlobalExceptionHandler {

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
