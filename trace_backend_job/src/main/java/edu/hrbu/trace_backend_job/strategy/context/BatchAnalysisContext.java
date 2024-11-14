package edu.hrbu.trace_backend_job.strategy.context;

import edu.hrbu.trace_backend_job.strategy.BatchAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class BatchAnalysisContext {

    @Resource
    private Map<String, BatchAnalysisStrategy> batchAnalysisStrategyMap;

    public BatchAnalysisStrategy getBatchAnalysisStrategy(String type) {
        return batchAnalysisStrategyMap.get(type);
    }

}
