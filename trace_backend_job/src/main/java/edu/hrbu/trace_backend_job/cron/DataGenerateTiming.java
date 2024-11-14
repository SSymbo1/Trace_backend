package edu.hrbu.trace_backend_job.cron;

import edu.hrbu.trace_backend_job.cron.service.DataGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@EnableScheduling
//  数据生成定时任务
public class DataGenerateTiming {

    @Resource
    private DataGenerateService dataGenerateService;

}
