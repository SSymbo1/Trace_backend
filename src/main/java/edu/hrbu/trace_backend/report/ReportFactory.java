package edu.hrbu.trace_backend.report;

import edu.hrbu.trace_backend.entity.dto.analysis.ReportQuery;

public interface ReportFactory {

    Report createReportSession(ReportQuery query);

}
