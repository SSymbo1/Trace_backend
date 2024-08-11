package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.monitor.InfoQuery;
import edu.hrbu.trace_backend.entity.dto.monitor.SummaryQuery;

public interface MonitorService {

    Result requestHistogramData(SummaryQuery query);

    Result requestSummaryData(SummaryQuery query);

    Result requestMonitorHistogramData(SummaryQuery query);

    Result requestMonitorPieData(SummaryQuery query);

    Result requestMonitorData(SummaryQuery query);

    Result requestMonitorInfoPie(InfoQuery query);

    Result requestMonitorInfo(InfoQuery query);

}
