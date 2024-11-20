package edu.hrbu.trace_backend_schedule.report;

import edu.hrbu.trace_backend_schedule.cron.ReportGenerateTiming;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ReportGenerateTests {
    @Resource
    public ReportGenerateTiming reportGenerateTiming;

    @Test
    void yearReportGenerateTest() {
        reportGenerateTiming.generateYearTraceReport();
    }

    @Test
    void monthReportGenerateTest() {
        reportGenerateTiming.generateMonthTraceReport();
    }

    @Test
    void quarterReportGenerateTest() {
        reportGenerateTiming.generateQuarterTraceReport();
    }
}
