package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.FarmQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface FarmAnalysisStrategy {

    Result getFarmAnalysisData(FarmQuery query);

}
