package edu.hrbu.trace_backend.global;

import edu.hrbu.trace_backend.service.AccountOperateService;
import edu.hrbu.trace_backend.service.EnterpriseOperateService;
import edu.hrbu.trace_backend.service.RoleOperateService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
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
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.addAccountInfo(..))")
    public void recordAddAccountInfoExecution() {}

    //  切点，匹配修改账户信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.editAccountInfo(..))")
    public void recordEditAccountInfoExecution() {}

    //  切点，匹配增加企业接口
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.addEnterprise(..)))")
    public void recordAddEnterpriseExecution(){}

    //  切点，匹配修改企业信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.editEnterprise(..)))")
    public void recordEditEnterpriseExecution(){}

    //  当执行修改账户操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditAccountInfoExecution()", returning = "result")
    public void recordEditAccountInfo(JoinPoint joinPoint, Object result) {
        accountOperateService.requestRecordAccountEdit(joinPoint, result);
    }

    //  当执行添加账户状态操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddAccountInfoExecution()", returning = "result")
    public void recordAddAccountInfo(JoinPoint joinPoint, Object result) {
       accountOperateService.requestRecordAccountAdd(joinPoint, result);
    }

    //  当执行修改账户状态操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend.controller.SystemController.accountStatueSet(..))")
    public void recordAccountStatueSet(JoinPoint joinPoint) {
       accountOperateService.requestRecordAccountStatueSet(joinPoint);
    }

    //  当执行解码密码操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend.controller.CommonDataController.decodePassword(..)))")
    public void recordDecodePassword(JoinPoint joinPoint) {
        accountOperateService.requestRecordDecodePassword(joinPoint);
    }

    //  当执行删除企业操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend.controller.SystemController.deleteEnterprise(..)))")
    public void recordEnterpriseDelete(JoinPoint joinPoint) {
        enterpriseOperateService.requestRecordEnterpriseDelete(joinPoint);
    }

    //  当执行添加企业操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddEnterpriseExecution()",returning = "result")
    public void recordAddEnterprise(JoinPoint joinPoint, Object result){
        enterpriseOperateService.requestRecordEnterpriseAdd(joinPoint, result);
    }

    //  当执行修改企业操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditEnterpriseExecution()",returning = "result")
    public void recordEditEnterprise(JoinPoint joinPoint, Object result){
        enterpriseOperateService.requestRecordEnterpriseEdit(joinPoint, result);
    }

}
