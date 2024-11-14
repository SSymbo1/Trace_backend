package edu.hrbu.trace_backend_job.report;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ReportQuery;

public interface Report {

    Result generateReport(ReportQuery query);

    Result reviewReport(ReportQuery query);

}
