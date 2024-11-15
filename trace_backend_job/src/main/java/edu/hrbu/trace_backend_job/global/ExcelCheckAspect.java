package edu.hrbu.trace_backend_job.global;

import edu.hrbu.trace_backend_business.service.ExcelCheckService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Aspect
@Component
//  上传表格校验切面
public class ExcelCheckAspect {

    @Resource
    private ExcelCheckService excelCheckService;

    @Before("execution(* edu.hrbu.trace_backend_job.controller.FileController.addProductByExcel(..))")
    public void checkProductExcel(JoinPoint joinPoint) {
        excelCheckService.requestProductExcelCheck(joinPoint);
    }

    @Before("execution(* edu.hrbu.trace_backend_job.controller.FileController.addApproachByExcel(..))")
    public void checkApproachExcel(JoinPoint joinPoint) {
        excelCheckService.requestApproachExcelCheck(joinPoint);
    }

    @Before("execution(* edu.hrbu.trace_backend_job.controller.FileController.addEntranceByExcel(..))")
    public void checkEntranceExcel(JoinPoint joinPoint) {
        excelCheckService.requestEntranceExcelCheck(joinPoint);
    }

}
