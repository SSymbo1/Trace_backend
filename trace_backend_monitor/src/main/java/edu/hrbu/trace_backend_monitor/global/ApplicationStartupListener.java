package edu.hrbu.trace_backend_monitor.global;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@Component
public class ApplicationStartupListener {

    @Resource
    private ServerProperties serverProperties;

    @EventListener
    public void handleContextRefreshedEvent(ContextRefreshedEvent event) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        log.info(
                "溯源系统应用监控服务已启动，地址为:http://{}:{}",
                inetAddress.getHostAddress(),
                serverProperties.getPort()
        );
    }

}
