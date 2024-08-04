package edu.hrbu.trace_backend.global;

import edu.hrbu.trace_backend.global.exception.ExcelException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend.service.ExcelCheckService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@Aspect
@Component
//  上传表格校验切面
public class ExcelCheckAspect {

    @Resource
    private ExcelCheckService excelCheckService;

    @Before("execution(* edu.hrbu.trace_backend.controller.FileController.addProductByExcel(..))")
    public void checkProductExcel(JoinPoint joinPoint) {
        excelCheckService.requestProductExcelCheck(joinPoint);
    }

}
