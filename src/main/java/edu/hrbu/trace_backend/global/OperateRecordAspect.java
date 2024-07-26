package edu.hrbu.trace_backend.global;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.AccountStatue;
import edu.hrbu.trace_backend.entity.dto.Decode;
import edu.hrbu.trace_backend.entity.enums.Operate;
import edu.hrbu.trace_backend.entity.po.AccountOperate;
import edu.hrbu.trace_backend.mapper.AccountOperateMapper;
import edu.hrbu.trace_backend.util.JwtUtil;
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
    private AccountOperateMapper accountOperateMapper;

    //  切点，匹配添加账户接口
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.addAccountInfo(..))")
    public void recordAddAccountInfoExecution() {}

    //  切点，匹配修改账户信息接口
    @Pointcut("execution(* edu.hrbu.trace_backend.controller.SystemController.editAccountInfo(..))")
    public void recordEditAccountInfoExecution() {}

    //  当执行修改账户操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordEditAccountInfoExecution()", returning = "result")
    public void recordEditAccountInfo(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid((Integer) returnResult.getData().get("createAid"))
                .operate(Operate.ACCOUNT_EDIT.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:修改账号信息");
    }

    //  当执行添加账户状态操作时写入敏感操作日志
    @AfterReturning(pointcut = "recordAddAccountInfoExecution()", returning = "result")
    public void recordAddAccountInfo(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = null;
        record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid((Integer) returnResult.getData().get("createAid"))
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        if (Objects.equals(returnResult.getData().get("createAid"), currentAccountId)) {
            record.setOperate(Operate.ACCOUNT_ADD_FAIL.getValue());
        } else {
            record.setOperate(Operate.ACCOUNT_ADD.getValue());
        }
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:创建账号");
    }

    //  当执行修改账户状态操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend.controller.SystemController.accountStatueSet(..))")
    public void recordAccountStatueSet(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        AccountStatue accountStatue = (AccountStatue) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(accountStatue.getAid())
                .operate(Operate.ACCOUNT_STATUE_SET.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:修改账号状态");
    }

    //  当执行解码密码操作时写入敏感操作日志
    @Before("execution(* edu.hrbu.trace_backend.controller.CommonDataController.decodePassword(..)))")
    public void recordDecodePassword(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Decode decode = (Decode) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(decode.getAid())
                .operate(Operate.DECODE.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:解码密码");
    }
}
