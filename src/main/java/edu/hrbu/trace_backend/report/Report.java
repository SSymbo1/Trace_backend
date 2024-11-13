package edu.hrbu.trace_backend.report;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.ReportQuery;

public interface Report {

    Result generateReport(ReportQuery query);

    Result reviewReport(ReportQuery query);

}
