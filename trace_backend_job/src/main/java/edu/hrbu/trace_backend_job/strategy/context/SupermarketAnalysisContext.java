package edu.hrbu.trace_backend_job.strategy.context;

import edu.hrbu.trace_backend_job.strategy.SupermarketAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class SupermarketAnalysisContext {

    @Resource
    private Map<String, SupermarketAnalysisStrategy> superMarketAnalysisStrategyMap;

    public SupermarketAnalysisStrategy getSuperMarketAnalysisStrategy(String type) {
        return superMarketAnalysisStrategyMap.get(type);
    }

}
