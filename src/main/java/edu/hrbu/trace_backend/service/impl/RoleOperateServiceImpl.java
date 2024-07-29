package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
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

    }

    @Override
    public void requestRecordEditRole(JoinPoint joinPoint, Object result) {

    }

    @Override
    public void requestRecordEnableAllRole(JoinPoint joinPoint) {

    }

    @Override
    public void requestRecordRoleStatueSet(JoinPoint joinPoint) {

    }
}




