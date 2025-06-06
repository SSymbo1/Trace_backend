package edu.hrbu.trace_backend_business.service;

import org.aspectj.lang.JoinPoint;

public interface ExcelCheckService {

    void requestProductExcelCheck(JoinPoint joinPoint);

    void requestApproachExcelCheck(JoinPoint joinPoint);

    void requestEntranceExcelCheck(JoinPoint joinPoint);

}
