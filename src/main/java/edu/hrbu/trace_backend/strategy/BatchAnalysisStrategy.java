package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.BatchQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface BatchAnalysisStrategy {

    Result getBatchAnalysisData(BatchQuery query);

}
