package edu.hrbu.trace_backend.strategy;

import edu.hrbu.trace_backend.entity.dto.analysis.MarketQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;

public interface SupermarketAnalysisStrategy {

    Result getSuperMarketAnalysisData(MarketQuery query);

}
