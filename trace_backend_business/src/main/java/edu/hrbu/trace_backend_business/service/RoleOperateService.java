package edu.hrbu.trace_backend_business.service;

import org.aspectj.lang.JoinPoint;

public interface RoleOperateService{

    void requestRecordAddRole(JoinPoint joinPoint, Object result);

    void requestRecordEditRole(JoinPoint joinPoint, Object result);

    void requestRecordRoleStatueSet(JoinPoint joinPoint);

    void requestRecordEnableAllRole(JoinPoint joinPoint);

    void requestRecordDisableAllRole(JoinPoint joinPoint);


}
