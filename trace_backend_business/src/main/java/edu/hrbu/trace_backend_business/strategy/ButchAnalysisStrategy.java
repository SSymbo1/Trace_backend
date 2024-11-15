package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.ButchQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface ButchAnalysisStrategy {

    Result getButchAnalysisData(ButchQuery query);

}
