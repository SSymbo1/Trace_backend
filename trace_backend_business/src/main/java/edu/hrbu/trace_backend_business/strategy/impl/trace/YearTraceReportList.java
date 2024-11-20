package edu.hrbu.trace_backend_business.strategy.impl.trace;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceReportList;
import edu.hrbu.trace_backend_business.entity.po.YearTraceReport;
import edu.hrbu.trace_backend_business.mapper.YearTraceReportMapper;
import edu.hrbu.trace_backend_business.strategy.TraceReportListStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("TraceYear")
public class YearTraceReportList implements TraceReportListStrategy {

    @Resource
    private YearTraceReportMapper yearTraceReportMapper;

    @Override
    public Map<String, Object> getTraceReportList(TraceQuery query) {
        int totalData = 0;
        Map<String, Object> pageData = new HashMap<>();
        List<TraceReportList> traceReportListList = new ArrayList<>();
        List<YearTraceReport> yearTraceReports;
        if (query.getDate() == null || query.getDate().isEmpty()) {
            yearTraceReports = yearTraceReportMapper.selectList(null);
        } else {
            yearTraceReports = yearTraceReportMapper.selectList(
                    new QueryWrapper<YearTraceReport>()
                            .eq("date", query.getDate())
            );
        }
        if (!yearTraceReports.isEmpty()) {
            for (YearTraceReport yearTraceReport : yearTraceReports) {
                traceReportListList.add(TraceReportList.builder()
                        .date(yearTraceReport.getDate())
                        .name(yearTraceReport.getDate() + "年追溯报告").build());
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
