package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.system.Role;
import edu.hrbu.trace_backend.entity.dto.system.RoleStatue;
import edu.hrbu.trace_backend.entity.enums.Operate;
import edu.hrbu.trace_backend.entity.po.RoleOperate;
import edu.hrbu.trace_backend.service.RoleOperateService;
import edu.hrbu.trace_backend.mapper.RoleOperateMapper;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class RoleOperateServiceImpl implements RoleOperateService {

    @Resource
    private RoleOperateMapper roleOperateMapper;

    @Override
    public void requestRecordAddRole(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        RoleOperate record = null;
        record = RoleOperate.builder()
                .oid(currentAccountId)
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        if (Objects.equals(returnResult.getData().get("createAid"), currentAccountId)) {
            record.setRid(1);
            record.setOperate(Operate.ROLE_ADD_FAIL.getValue());
        } else {
            record.setRid((Integer) returnResult.getData().get("createAid"));
            record.setOperate(Operate.ROLE_ADD.getValue());
        }
        roleOperateMapper.insert(record);
        log.info("已记录敏感操作:创建角色");
    }

    @Override
    public void requestRecordDisableAllRole(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        DateTime operateTime = new DateTime(DateTime.now());
        RoleOperate record = null;
        record = RoleOperate.builder()
                .oid(currentAccountId)
                .rid(1)
                .operate(Operate.DISABLE_ALL_ROLE.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        roleOperateMapper.insert(record);
        log.info("已记录敏感操作:禁用所有角色");
    }

    @Override
    public void requestRecordEditRole(JoinPoint joinPoint, Object result) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Role role = (Role) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        RoleOperate record = null;
        record = RoleOperate.builder()
                .oid(currentAccountId)
                .rid(role.getRid())
                .operate(Operate.ROLE_EDIT.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        roleOperateMapper.insert(record);
        log.info("已记录敏感操作:修改角色信息");
    }

    @Override
    public void requestRecordEnableAllRole(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        DateTime operateTime = new DateTime(DateTime.now());
        RoleOperate record = null;
        record = RoleOperate.builder()
                .oid(currentAccountId)
                .rid(1)
                .operate(Operate.ENABLE_ALL_ROLE.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        roleOperateMapper.insert(record);
        log.info("已记录敏感操作:启用所有角色");
    }

    @Override
    public void requestRecordRoleStatueSet(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        RoleStatue roleStatue = (RoleStatue) args[0];
        DateTime operateTime = new DateTime(DateTime.now());
        RoleOperate record = null;
        record = RoleOperate.builder()
                .oid(currentAccountId)
                .rid(roleStatue.getRid())
                .operate(Operate.ROLE_STATUE_SET.getValue())
                .operateTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        roleOperateMapper.insert(record);
        log.info("已记录敏感操作:修改角色状态");
    }
}




