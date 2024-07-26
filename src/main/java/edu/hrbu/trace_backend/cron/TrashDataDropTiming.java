package edu.hrbu.trace_backend.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableScheduling
//  定时删除垃圾数据
public class TrashDataDropTiming {

}
