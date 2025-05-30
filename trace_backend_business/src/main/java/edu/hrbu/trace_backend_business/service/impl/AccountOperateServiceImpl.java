package edu.hrbu.trace_backend_business.service.impl;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend_business.entity.OnlineContext;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.Decode;
import edu.hrbu.trace_backend_business.entity.dto.system.AccountStatue;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.enums.Operate;
import edu.hrbu.trace_backend_business.entity.po.AccountOperate;
import edu.hrbu.trace_backend_business.mapper.AccountOperateMapper;
import edu.hrbu.trace_backend_business.service.AccountOperateService;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class AccountOperateServiceImpl implements AccountOperateService {

    @Resource
    private AccountOperateMapper accountOperateMapper;

    @Override
    public void requestRecordAccountAdd(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = null;
        record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid((Integer) returnResult.getData().get("createAid"))
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        if (Objects.equals(returnResult.getData().get("createAid"), currentAccountId)) {
            record.setOperate(Operate.ACCOUNT_ADD_FAIL.getValue());
        } else {
            record.setOperate(Operate.ACCOUNT_ADD.getValue());
        }
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:创建账号");
    }

    @Override
    public void requestRecordAccountEdit(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid((Integer) returnResult.getData().get("createAid"))
                .operate(Operate.ACCOUNT_EDIT.getValue())
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:修改账号信息");
    }

    @Override
    public void requestRecordAccountStatueSet(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        AccountStatue accountStatue = (AccountStatue) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(accountStatue.getAid())
                .operate(Operate.ACCOUNT_STATUE_SET.getValue())
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:修改账号状态");
    }

    @Override
    public void requestRecordDecodePassword(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Decode decode = (Decode) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(decode.getAid())
                .operate(Operate.DECODE.getValue())
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:解码密码");
    }

    @Override
    public void requestRecordDisableAllAccount(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(currentAccountId)
                .operate(Operate.DISABLE_ALL_ACCOUNT.getValue())
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:禁用所有账户");
    }

    @Override
    public void requestRecordEnableAllAccount(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        DateTime operateTime = new DateTime(DateTime.now());
        AccountOperate record = AccountOperate.builder()
                .oid(currentAccountId)
                .aid(currentAccountId)
                .operate(Operate.ENABLE_ALL_ACCOUNT.getValue())
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        accountOperateMapper.insert(record);
        log.info("已记录敏感操作:启用所有账户");
    }
}
