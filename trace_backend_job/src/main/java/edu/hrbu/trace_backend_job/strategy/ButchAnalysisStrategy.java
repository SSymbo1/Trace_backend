package edu.hrbu.trace_backend_job.strategy;

import edu.hrbu.trace_backend_job.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ButchQuery;

public interface ButchAnalysisStrategy {

    Result getButchAnalysisData(ButchQuery query);

}
