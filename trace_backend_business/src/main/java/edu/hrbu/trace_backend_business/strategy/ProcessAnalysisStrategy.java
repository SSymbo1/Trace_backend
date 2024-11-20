package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.ProcessQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface ProcessAnalysisStrategy {

    Result getProcessAnalysisData(ProcessQuery query);

}
