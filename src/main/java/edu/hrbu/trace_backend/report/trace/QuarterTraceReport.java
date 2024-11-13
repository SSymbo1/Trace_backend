package edu.hrbu.trace_backend.report.trace;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend.report.Report;
import org.springframework.stereotype.Component;

@Component("QuarterTrace")
public class QuarterTraceReport implements Report {

    @Override
    public Result generateReport(ReportQuery query) {
        return null;
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        return null;
    }
}
