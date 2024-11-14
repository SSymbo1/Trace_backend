package edu.hrbu.trace_backend_job.service.impl;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend_job.entity.OnlineContext;
import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.enums.Format;
import edu.hrbu.trace_backend_job.entity.enums.Operate;
import edu.hrbu.trace_backend_job.entity.po.EnterpriseOperate;
import edu.hrbu.trace_backend_job.service.EnterpriseOperateService;
import edu.hrbu.trace_backend_job.mapper.EnterpriseOperateMapper;
import edu.hrbu.trace_backend_job.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Service
public class EnterpriseOperateServiceImpl implements EnterpriseOperateService {

    @Resource
    private EnterpriseOperateMapper enterpriseOperateMapper;

    @Override
    public void requestRecordEnterpriseAdd(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        EnterpriseOperate record = null;
        record = EnterpriseOperate.builder()
                .oid(currentAccountId)
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        if (Objects.equals(returnResult.getData().get("createAid"), currentAccountId)) {
            record.setEid(1);
            record.setOperate(Operate.ENTERPRISE_ADD_FAIL.getValue());
        } else {
            record.setEid((Integer) returnResult.getData().get("createAid"));
            record.setOperate(Operate.ENTERPRISE_ADD.getValue());
        }
        enterpriseOperateMapper.insert(record);
        log.info("已记录敏感操作:创建企业");
    }

    @Override
    public void requestRecordEnterpriseEdit(JoinPoint joinPoint, Object result) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Result returnResult = (Result) result;
        DateTime operateTime = new DateTime(DateTime.now());
        EnterpriseOperate record = null;
        record = EnterpriseOperate.builder()
                .oid(currentAccountId)
                .operate(Operate.ENTERPRISE_EDIT.getValue())
                .eid((Integer) returnResult.getData().get("createAid"))
                .operateTime(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build();
        enterpriseOperateMapper.insert(record);
        log.info("已记录敏感操作:修改企业信息");
    }
}




