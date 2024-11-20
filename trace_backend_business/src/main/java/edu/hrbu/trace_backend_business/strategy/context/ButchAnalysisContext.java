package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.ButchAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class ButchAnalysisContext {

    @Resource
    private Map<String, ButchAnalysisStrategy> butchAnalysisStrategyMap;

    public ButchAnalysisStrategy getButchAnalysisStrategy(String type) {
        return butchAnalysisStrategyMap.get(type);
    }

}
