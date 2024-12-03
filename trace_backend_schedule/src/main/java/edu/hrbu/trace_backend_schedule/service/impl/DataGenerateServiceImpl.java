package edu.hrbu.trace_backend_schedule.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import edu.hrbu.trace_backend_business.entity.po.PlatformData;
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.PlatformDataMapper;
import edu.hrbu.trace_backend_schedule.service.DataGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class DataGenerateServiceImpl implements DataGenerateService {

    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private PlatformDataMapper platformDataMapper;

    @Override
    public void recordEnterpriseCount() {
        DateTime currentTime = new DateTime(DateTime.now());
        String dateFormat = DateUtil.format(currentTime, Format.FULL_DATE_FORMAT.getValue());
        log.info("执行统计{}企业数量任务", dateFormat);
        Long count = enterpriseMapper.selectCount(
                new QueryWrapper<Enterprise>()
                        .ne("del", 1)
                        .and(eid -> eid.ne("eid", 1))
        );
        PlatformData data = platformDataMapper.selectOne(
                new QueryWrapper<PlatformData>()
                        .eq("date", dateFormat)
        );
        if (data == null) {
            platformDataMapper.insert(
                    PlatformData.builder()
                            .count(Math.toIntExact(count))
                            .name("enterprise")
                            .date(dateFormat).build()
            );
        } else {
            platformDataMapper.updateById(
                    PlatformData.builder()
                            .pid(data.getPid())
                            .count(Math.toIntExact(count)).build()
            );
        }
        log.info("{}企业数量为:{}", dateFormat, count);
    }

}
