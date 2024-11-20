package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.MarketQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;

public interface SupermarketAnalysisStrategy {

    Result getSuperMarketAnalysisData(MarketQuery query);

}
