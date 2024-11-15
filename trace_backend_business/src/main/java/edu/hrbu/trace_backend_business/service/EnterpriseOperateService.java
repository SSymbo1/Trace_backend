package edu.hrbu.trace_backend_business.service;

import org.aspectj.lang.JoinPoint;

public interface EnterpriseOperateService{

    void requestRecordEnterpriseAdd(JoinPoint joinPoint, Object result);

    void requestRecordEnterpriseEdit(JoinPoint joinPoint, Object result);

}
