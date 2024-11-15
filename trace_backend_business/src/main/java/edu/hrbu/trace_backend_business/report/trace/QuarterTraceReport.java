package edu.hrbu.trace_backend_business.report.trace;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_business.report.Report;
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
