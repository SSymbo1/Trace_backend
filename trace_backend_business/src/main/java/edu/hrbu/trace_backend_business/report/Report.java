package edu.hrbu.trace_backend_business.report;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;

public interface Report {

    Result generateReport(ReportQuery query);

    Result reviewReport(ReportQuery query);

}
