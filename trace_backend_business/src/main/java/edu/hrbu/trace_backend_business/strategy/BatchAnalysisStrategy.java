package edu.hrbu.trace_backend_business.strategy;


import edu.hrbu.trace_backend_business.entity.dto.analysis.BatchQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface BatchAnalysisStrategy {

    Result getBatchAnalysisData(BatchQuery query);

}
