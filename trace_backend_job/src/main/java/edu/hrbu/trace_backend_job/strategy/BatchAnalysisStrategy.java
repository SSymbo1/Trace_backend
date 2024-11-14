package edu.hrbu.trace_backend_job.strategy;


import edu.hrbu.trace_backend_job.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.BatchQuery;

public interface BatchAnalysisStrategy {

    Result getBatchAnalysisData(BatchQuery query);

}
