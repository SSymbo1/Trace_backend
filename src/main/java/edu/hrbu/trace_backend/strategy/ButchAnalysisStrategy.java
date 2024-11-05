package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.ButchQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface ButchAnalysisStrategy {

    Result getButchAnalysisData(ButchQuery query);

}
