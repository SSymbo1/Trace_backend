package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.BatchQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.MarketQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.OperationsQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.ProcessQuery;

public interface AnalysisService {

    Result requestOperationsInfo(OperationsQuery query);

    Result requestSupermarketInfo(MarketQuery query);

    Result requestBatchInfo(BatchQuery query);

    Result requestProcessInfo(ProcessQuery query);

}
