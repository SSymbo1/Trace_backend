package edu.hrbu.trace_backend.report;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class CommonReportFactory {

    @Resource
    private Map<String, ReportFactory> reportFactoryMap;

    public ReportFactory getReportFactory(String type) {
        return reportFactoryMap.get(type);
    }

}
