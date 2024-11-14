package edu.hrbu.trace_backend_job.strategy;

import edu.hrbu.trace_backend_job.entity.dto.analysis.OperationsQuery;

import java.util.List;
import java.util.Map;

public interface MarketOperationStrategy {

    List<Map<String, Integer>> getMarketOperationOriginData(OperationsQuery query);

    List<Map<String, Double>> getMarketOperationYOYData(OperationsQuery query, List<Map<String, Integer>> origin);

    List<Map<String, Double>> getMarketOperationQOQData(OperationsQuery query, List<Map<String, Integer>> origin);
}
