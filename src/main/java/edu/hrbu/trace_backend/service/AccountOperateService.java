package edu.hrbu.trace_backend.service;

import org.aspectj.lang.JoinPoint;

public interface AccountOperateService {

    void requestRecordAccountEdit(JoinPoint joinPoint, Object result);

    void requestRecordAccountAdd(JoinPoint joinPoint, Object result);

    void requestRecordAccountStatueSet(JoinPoint joinPoint);

    void requestRecordDecodePassword(JoinPoint joinPoint);

    void requestRecordEnableAllAccount(JoinPoint joinPoint);

    void requestRecordDisableAllAccount(JoinPoint joinPoint);

}
