package edu.hrbu.trace_backend.report.struct;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend.report.Report;
import org.springframework.stereotype.Component;

@Component("YearStruct")
public class YearStructReport implements Report {
    @Override
    public Result generateReport(ReportQuery query) {
        return null;
    }

    @Override
    public void reviewReport(ReportQuery query) {

    }
}
