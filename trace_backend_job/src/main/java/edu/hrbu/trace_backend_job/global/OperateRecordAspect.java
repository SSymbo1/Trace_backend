package edu.hrbu.trace_backend_job.global;

import edu.hrbu.trace_backend_business.service.AccountOperateService;
import edu.hrbu.trace_backend_business.service.EnterpriseOperateService;
import edu.hrbu.trace_backend_business.service.RoleOperateService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
//  敏感信息记录处理切面
public class OperateRecordAspect {

    @Resource
    private AccountOperateService accountOperateService;
    @Resource
    private EnterpriseOperateService enterpriseOperateService;
    @Resource
    private RoleOperateService roleOperateService;

    //  切点，匹配添加账户接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.addAccountInfo(..))")
    public void recordAddAccountInfoExecution() {
    }

    //  切点，匹配修改账户信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.editAccountInfo(..))")
    public void recordEditAccountInfoExecution() {
    }

    //  切点，匹配增加企业接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.addEnterprise(..)))")
    public void recordAddEnterpriseExecution() {
    }

    //  切点，匹配修改企业信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.editEnterprise(..)))")
    public void recordEditEnterpriseExecution() {
    }

    //  切点，匹配添加角色接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.addRole(..)))")
    public void recordAddRoleExecution() {
    }

    //  切点，匹配修改角色信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend_job.controller.SystemController.editRole(..)))")
    public void recordEditRoleExecution() {
    }

    //  当执行修改账户操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditAccountInfoExecution()", returning = "result")
    public void recordEditAccountInfo(JoinPoint joinPoint, Object result) {
        accountOperateService.requestRecordAccountEdit(joinPoint, result);
    }

    //  当执行添加账户操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddAccountInfoExecution()", returning = "result")
    public void recordAddAccountInfo(JoinPoint joinPoint, Object result) {
        accountOperateService.requestRecordAccountAdd(joinPoint, result);
    }

    //  当执行修改账户状态操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.accountStatueSet(..))")
    public void recordAccountStatueSet(JoinPoint joinPoint) {
        accountOperateService.requestRecordAccountStatueSet(joinPoint);
    }

    //  当执行解码密码操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.CommonDataController.decodePassword(..)))")
    public void recordDecodePassword(JoinPoint joinPoint) {
        accountOperateService.requestRecordDecodePassword(joinPoint);
    }

    //  当执行启用所有账户操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.enableAllAccount(..))")
    public void recordEnableAllAccount(JoinPoint joinPoint) {
        accountOperateService.requestRecordEnableAllAccount(joinPoint);
    }

    //  当执行禁用所有账户操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.disableAllAccount(..))")
    public void recordDisableAllAccount(JoinPoint joinPoint) {
        accountOperateService.requestRecordDisableAllAccount(joinPoint);
    }

    //  当执行添加企业操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddEnterpriseExecution()", returning = "result")
    public void recordAddEnterprise(JoinPoint joinPoint, Object result) {
        enterpriseOperateService.requestRecordEnterpriseAdd(joinPoint, result);
    }

    //  当执行修改企业操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditEnterpriseExecution()", returning = "result")
    public void recordEditEnterprise(JoinPoint joinPoint, Object result) {
        enterpriseOperateService.requestRecordEnterpriseEdit(joinPoint, result);
    }

    //  当执行添加角色操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddRoleExecution()", returning = "result")
    public void recordAddRole(JoinPoint joinPoint, Object result) {
        roleOperateService.requestRecordAddRole(joinPoint, result);
    }

    //  当执行修改角色操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditRoleExecution()", returning = "result")
    public void recordEditRole(JoinPoint joinPoint, Object result) {
        roleOperateService.requestRecordEditRole(joinPoint, result);
    }

    //  当执行修改角色状态操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.roleStatueSet(..))")
    public void recordRoleStatueSet(JoinPoint joinPoint) {
        roleOperateService.requestRecordRoleStatueSet(joinPoint);
    }

    //  当执行启用所有角色操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.enableAllRole(..))")
    public void recordEnableAllRole(JoinPoint joinPoint) {
        roleOperateService.requestRecordEnableAllRole(joinPoint);
    }

    //  当执行禁用所有角色操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend_job.controller.SystemController.disableAllRole(..))")
    public void recordDisableAllRole(JoinPoint joinPoint) {
        roleOperateService.requestRecordDisableAllRole(joinPoint);
    }

}
