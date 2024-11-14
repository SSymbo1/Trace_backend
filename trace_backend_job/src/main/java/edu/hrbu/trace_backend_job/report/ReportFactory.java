package edu.hrbu.trace_backend_job.report;

import edu.hrbu.trace_backend_job.entity.dto.analysis.ReportQuery;

public interface ReportFactory {

    Report createReportSession(ReportQuery query);

}
