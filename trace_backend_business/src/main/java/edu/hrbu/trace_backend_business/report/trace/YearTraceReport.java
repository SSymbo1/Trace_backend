package edu.hrbu.trace_backend_business.report.trace;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.mapper.*;
import edu.hrbu.trace_backend_business.report.Report;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("YearTrace")
public class YearTraceReport implements Report {

    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public Result generateReport(ReportQuery query) {
        DateTime date = new DateTime(DateTime.now());
        edu.hrbu.trace_backend_business.entity.po.YearTraceReport report = edu.hrbu.trace_backend_business.entity.po.YearTraceReport.builder()
                .operateDate(date.toString(Format.FULL_TIME_FORMAT.toString()))
                .date(query.getDate()).build();
        return null;
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        return null;
    }
}
