package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.TraceReportListStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class TraceReportListContext {

    @Resource
    private Map<String, TraceReportListStrategy> traceReportListStrategyMap;

    public TraceReportListStrategy getTraceReportListStrategy(String type) {
        return traceReportListStrategyMap.get(type);
    }

}
