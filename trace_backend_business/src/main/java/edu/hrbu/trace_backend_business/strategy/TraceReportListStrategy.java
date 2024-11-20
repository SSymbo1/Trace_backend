package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceQuery;

import java.util.Map;

public interface TraceReportListStrategy {

    Map<String, Object> getTraceReportList(TraceQuery query);

}
