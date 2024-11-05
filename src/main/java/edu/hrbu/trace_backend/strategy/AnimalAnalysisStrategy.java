package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.AnimalQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface AnimalAnalysisStrategy {

    Result getAnimalAnalysisData(AnimalQuery query);

}
