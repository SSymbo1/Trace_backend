package edu.hrbu.trace_backend_job.strategy.context;

import edu.hrbu.trace_backend_job.strategy.PlantAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class PlantAnalysisContext {

    @Resource
    private Map<String, PlantAnalysisStrategy> plantAnalysisStrategyMap;

    public PlantAnalysisStrategy getPlantAnalysisStrategy(String type){
        return plantAnalysisStrategyMap.get(type);
    }

}
