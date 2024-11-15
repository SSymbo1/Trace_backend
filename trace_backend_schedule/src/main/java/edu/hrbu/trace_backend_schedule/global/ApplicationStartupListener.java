package edu.hrbu.trace_backend_schedule.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationStartupListener {

    @EventListener
    public void handleContextRefreshedEvent(ContextRefreshedEvent event) {
        log.info("溯源系统定时任务服务已启动!");
    }

}
