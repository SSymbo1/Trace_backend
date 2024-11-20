package edu.hrbu.trace_backend_schedule.cron;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.report.CommonReportFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@EnableScheduling
//  定时生成追溯报告
public class ReportGenerateTiming {

    @Resource
    private CommonReportFactory commonReportFactory;

    @Scheduled(cron = "0 59 23 31 12 *")
    public void generateYearTraceReport() {
        DateTime date = new DateTime(DateTime.now());
        String localYear = date.toString(Format.ONLY_YEAR_FORMAT.getValue());
        log.info("正在生成{}年追溯报告", localYear);
        ReportQuery query = ReportQuery.builder()
                .date(localYear)
                .report("TraceReport")
                .type("YearTrace").build();
        Result result = commonReportFactory
                .getReportFactory(query.getReport())
                .createReportSession(query)
                .generateReport(query);
        log.info(result.getMessage());
    }

    @Scheduled(cron = "0 59 23 28-31 * *")
    public void generateMonthTraceReport() {
        if (DateUtil.isLastDayOfMonth(DateTime.now())) {
            DateTime date = new DateTime(DateTime.now());
            String localMonth = date.toString(Format.YEAR_MONTH_FORMAT.getValue());
            log.info("正在生成{}月追溯报告", localMonth);
            ReportQuery query = ReportQuery.builder()
                    .date(localMonth)
                    .report("TraceReport")
                    .type("MonthTrace").build();
            Result result = commonReportFactory
                    .getReportFactory(query.getReport())
                    .createReportSession(query)
                    .generateReport(query);
            log.info(result.getMessage());
        }
    }

    @Scheduled(cron = "0 59 23 L 3,6,9,12 ?")
    public void generateQuarterTraceReport() {
        DateTime date = new DateTime(DateTime.now());
        String localMonth = date.toString(Format.YEAR_MONTH_FORMAT.getValue());
        log.info("正在生成{}季度追溯报告", localMonth);
        ReportQuery query = ReportQuery.builder()
                .date(localMonth)
                .report("TraceReport")
                .type("QuarterTrace").build();
        Result result = commonReportFactory
                .getReportFactory(query.getReport())
                .createReportSession(query)
                .generateReport(query);
        log.info(result.getMessage());
    }

}
