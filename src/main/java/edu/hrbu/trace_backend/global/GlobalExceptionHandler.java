package edu.hrbu.trace_backend.global;

import edu.hrbu.trace_backend.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public Result exceptionHandler(Exception exception) {
        log.error("出错啦：{}", exception.getMessage());
        return Result.fail("出错啦，请稍后再试!");
    }
}
