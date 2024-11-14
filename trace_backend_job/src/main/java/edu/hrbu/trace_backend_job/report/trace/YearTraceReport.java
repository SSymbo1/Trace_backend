package edu.hrbu.trace_backend_job.report.trace;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_job.report.Report;
import org.springframework.stereotype.Component;

@Component("YearTrace")
public class YearTraceReport implements Report {

    @Override
    public Result generateReport(ReportQuery query) {
        return null;
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        return null;
    }
}
