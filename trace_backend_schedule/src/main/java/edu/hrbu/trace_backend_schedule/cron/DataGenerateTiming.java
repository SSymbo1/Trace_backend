package edu.hrbu.trace_backend_schedule.cron;

import edu.hrbu.trace_backend_schedule.service.DataGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@EnableScheduling
//  数据生成定时任务
public class DataGenerateTiming {

    @Resource
    private DataGenerateService dataGenerateService;

    @Scheduled(cron = "0 59 23 * * ?")
    public void recordEnterpriseTotal() {
        dataGenerateService.recordEnterpriseCount();
    }

}
