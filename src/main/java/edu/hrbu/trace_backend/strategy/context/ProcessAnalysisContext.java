package edu.hrbu.trace_backend.strategy.context;

import edu.hrbu.trace_backend.strategy.ProcessAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ProcessAnalysisContext {

    @Resource
    private Map<String, ProcessAnalysisStrategy> processAnalysisStrategyMap;

    public ProcessAnalysisStrategy getProcessAnalysisStrategy(String type) {
        return processAnalysisStrategyMap.get(type);
    }

}
