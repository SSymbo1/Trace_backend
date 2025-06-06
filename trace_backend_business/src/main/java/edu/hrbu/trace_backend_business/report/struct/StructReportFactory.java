package edu.hrbu.trace_backend_business.report.struct;

import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_business.report.Report;
import edu.hrbu.trace_backend_business.report.ReportFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component("StructReport")
public class StructReportFactory implements ReportFactory {

    @Resource
    private Map<String, Report> structReportMap;

    @Override
    public Report createReportSession(ReportQuery query) {
        return structReportMap.get(query.getType());
    }
}
