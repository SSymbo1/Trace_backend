package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.ProcessQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface ProcessAnalysisStrategy {

    Result getProcessAnalysisData(ProcessQuery query);

}
