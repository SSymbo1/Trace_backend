package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.FarmQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface FarmAnalysisStrategy {

    Result getFarmAnalysisData(FarmQuery query);

}
