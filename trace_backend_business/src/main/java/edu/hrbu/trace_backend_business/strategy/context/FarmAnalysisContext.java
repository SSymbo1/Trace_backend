package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.FarmAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class FarmAnalysisContext {

    @Resource
    private Map<String, FarmAnalysisStrategy> farmAnalysisStrategyMap;

    public FarmAnalysisStrategy getFarmAnalysisStrategy(String type) {
        return farmAnalysisStrategyMap.get(type);
    }

}
