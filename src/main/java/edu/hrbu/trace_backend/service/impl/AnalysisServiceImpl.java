package edu.hrbu.trace_backend.service.impl;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.*;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.service.AnalysisService;
import edu.hrbu.trace_backend.strategy.BatchAnalysisStrategy;
import edu.hrbu.trace_backend.strategy.MarketOperationStrategy;
import edu.hrbu.trace_backend.strategy.ProcessAnalysisStrategy;
import edu.hrbu.trace_backend.strategy.SupermarketAnalysisStrategy;
import edu.hrbu.trace_backend.strategy.context.BatchAnalysisContext;
import edu.hrbu.trace_backend.strategy.context.MarketOperationContext;
import edu.hrbu.trace_backend.strategy.context.ProcessAnalysisContext;
import edu.hrbu.trace_backend.strategy.context.SupermarketAnalysisContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnalysisServiceImpl implements AnalysisService {
    @Resource
    private MarketOperationContext marketOperationContext;
    @Resource
    private SupermarketAnalysisContext superMarketAnalysisContext;
    @Resource
    private BatchAnalysisContext batchAnalysisContext;
    @Resource
    private ProcessAnalysisContext processAnalysisContext;

    @Override
    public Result requestOperationsInfo(OperationsQuery query) {
        List<Map<String, Integer>> originData = getOperationsOriginData(query);
        List<Map<String, Double>> YOYData = getOperationsYOYData(query, originData);
        List<Map<String, Double>> QOQData = getOperationsQOQData(query, originData);
        return Result.ok(Message.GET_MARKET_OPERATIONS.getValue()).data("operations", Operations.builder()
                .originData(originData)
                .YOYData(YOYData)
                .QOQData(QOQData).build());
    }

    @Override
    public Result requestSupermarketInfo(MarketQuery query) {
        SupermarketAnalysisStrategy strategy = superMarketAnalysisContext.getSuperMarketAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_SUPERMARKET_ANALYSIS.getValue())
                .data("supermarket", strategy.getSuperMarketAnalysisData(query));
    }

    @Override
    public Result requestBatchInfo(BatchQuery query) {
        BatchAnalysisStrategy strategy = batchAnalysisContext.getBatchAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_BATCH_ANALYSIS.getValue())
                .data("batch", strategy.getBatchAnalysisData(query));
    }

    @Override
    public Result requestProcessInfo(ProcessQuery query) {
        ProcessAnalysisStrategy strategy = processAnalysisContext.getProcessAnalysisStrategy(query.getType());
        return null;
    }

    private List<Map<String, Integer>> getOperationsOriginData(OperationsQuery query) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationOriginData(query);
    }

    private List<Map<String, Double>> getOperationsYOYData(OperationsQuery query, List<Map<String, Integer>> origin) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationYOYData(query, origin);
    }

    private List<Map<String, Double>> getOperationsQOQData(OperationsQuery query, List<Map<String, Integer>> origin) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationQOQData(query, origin);
    }

}
