package edu.hrbu.trace_backend.cron;

import edu.hrbu.trace_backend.cron.service.ReportGenerateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@EnableScheduling
//  定时生成追溯报告
public class ReportGenerateTiming {

    @Resource
    private ReportGenerateService reportGenerateService;

}
