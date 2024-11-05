package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.PlantQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface PlantAnalysisStrategy {

    Result getPlantAnalysisData(PlantQuery query);

}
