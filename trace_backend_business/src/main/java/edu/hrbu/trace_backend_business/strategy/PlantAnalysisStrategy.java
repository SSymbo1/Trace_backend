package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.PlantQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface PlantAnalysisStrategy {

    Result getPlantAnalysisData(PlantQuery query);

}
