package edu.hrbu.trace_backend.report.trace;

import edu.hrbu.trace_backend.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend.report.Report;
import edu.hrbu.trace_backend.report.ReportFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("TraceReport")
public class TraceReportFactory implements ReportFactory {

    @Resource
    private Map<String, Report> traceReportMap;

    @Override
    public Report createReportSession(ReportQuery query) {
        return traceReportMap.get(query.getType());
    }

}
