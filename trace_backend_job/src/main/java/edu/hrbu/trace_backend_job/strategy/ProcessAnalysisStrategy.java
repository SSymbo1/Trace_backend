package edu.hrbu.trace_backend_job.strategy;

import edu.hrbu.trace_backend_job.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ProcessQuery;

public interface ProcessAnalysisStrategy {

    Result getProcessAnalysisData(ProcessQuery query);

}
