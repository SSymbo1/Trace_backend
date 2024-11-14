package edu.hrbu.trace_backend_job.report.trace;

import edu.hrbu.trace_backend_job.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_job.report.Report;
import edu.hrbu.trace_backend_job.report.ReportFactory;
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
