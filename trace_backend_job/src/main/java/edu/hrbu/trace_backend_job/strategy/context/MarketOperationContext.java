package edu.hrbu.trace_backend_job.strategy.context;

import edu.hrbu.trace_backend_job.strategy.MarketOperationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class MarketOperationContext {
    @Resource
    private Map<String, MarketOperationStrategy> marketOperationStrategyMap;

    public MarketOperationStrategy getMarketOperationStrategy(String type) {
        return marketOperationStrategyMap.get(type);
    }
}
