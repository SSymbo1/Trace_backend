package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.StructAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class StructAnalysisContext {

    @Resource
    private Map<String, StructAnalysisStrategy> structAnalysisStrategyMap;

    public StructAnalysisStrategy getStructAnalysisStrategy(String type) {
        return structAnalysisStrategyMap.get(type);
    }

}
