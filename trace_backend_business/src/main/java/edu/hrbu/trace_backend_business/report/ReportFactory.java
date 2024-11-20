package edu.hrbu.trace_backend_business.report;

import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;

public interface ReportFactory {

    Report createReportSession(ReportQuery query);

}
