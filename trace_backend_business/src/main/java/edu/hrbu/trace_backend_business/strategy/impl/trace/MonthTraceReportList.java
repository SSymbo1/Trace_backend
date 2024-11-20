package edu.hrbu.trace_backend_business.strategy.impl.trace;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceReportList;
import edu.hrbu.trace_backend_business.entity.po.MonthTraceReport;
import edu.hrbu.trace_backend_business.mapper.MonthTraceReportMapper;
import edu.hrbu.trace_backend_business.strategy.TraceReportListStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("TraceMonth")
public class MonthTraceReportList implements TraceReportListStrategy {

    @Resource
    private MonthTraceReportMapper monthTraceReportMapper;

    @Override
    public Map<String, Object> getTraceReportList(TraceQuery query) {
        int totalData = 0;
        Map<String, Object> pageData = new HashMap<>();
        List<TraceReportList> traceReportListList = new ArrayList<>();
        List<MonthTraceReport> monthTraceReports;
        if (query.getDate() == null || query.getDate().isEmpty()) {
            monthTraceReports = monthTraceReportMapper.selectList(null);
        } else {
            monthTraceReports = monthTraceReportMapper.selectList(
                    new QueryWrapper<MonthTraceReport>()
                            .eq("date", query.getDate())
            );
        }
        if (!monthTraceReports.isEmpty()) {
            for (MonthTraceReport monthTraceReport : monthTraceReports) {
                traceReportListList.add(TraceReportList.builder()
                        .date(monthTraceReport.getDate())
                        .name(monthTraceReport.getDate() + "月追溯报告").build());
            }
        }
        int startIndex = (query.getCurrentPage() - 1) * query.getPageSize();
        int endIndex = Math.min(startIndex + query.getPageSize(), traceReportListList.size());
        List<TraceReportList> data = traceReportListList.subList(startIndex, endIndex);
        pageData.put("data", data);
        pageData.put("total", data.size());
        pageData.put("size", query.getPageSize());
        pageData.put("current", query.getCurrentPage());
        pageData.put("pages", data.size() / query.getPageSize() + 1);
        pageData.put("totalData", totalData);
        return pageData;
    }
}
