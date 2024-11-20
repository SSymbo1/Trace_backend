package edu.hrbu.trace_backend_business.strategy.context;

import edu.hrbu.trace_backend_business.strategy.AnimalAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class AnimalAnalysisContext {

    @Resource
    private Map<String, AnimalAnalysisStrategy> animalAnalysisStrategyMap;

    public AnimalAnalysisStrategy getAnimalAnalysisStrategy(String type) {
        return animalAnalysisStrategyMap.get(type);
    }

}
