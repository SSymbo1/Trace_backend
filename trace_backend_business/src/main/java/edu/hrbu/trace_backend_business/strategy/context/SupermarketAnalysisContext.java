package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.SupermarketAnalysisStrategy;
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
