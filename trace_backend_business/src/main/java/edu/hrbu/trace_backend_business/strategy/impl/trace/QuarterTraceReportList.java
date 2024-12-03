package edu.hrbu.trace_backend_business.strategy.impl.trace;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.TraceReportList;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.po.QuarterTraceReport;
import edu.hrbu.trace_backend_business.mapper.QuarterTraceReportMapper;
import edu.hrbu.trace_backend_business.strategy.TraceReportListStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("TraceQuarter")
public class QuarterTraceReportList implements TraceReportListStrategy {

    @Resource
    private QuarterTraceReportMapper quarterTraceReportMapper;

    @Override
    public Map<String, Object> getTraceReportList(TraceQuery query) {
        int totalData = 0;
        Map<String, Object> pageData = new HashMap<>();
        List<TraceReportList> traceReportListList = new ArrayList<>();
        List<QuarterTraceReport> quarterTraceReports;
        if (query.getDate() == null || query.getDate().isEmpty()) {
            quarterTraceReports = quarterTraceReportMapper.selectList(null);
        } else {
            String quarter = query.getDate().substring(4);
            String year = query.getDate().substring(0, 4);
            List<String> condition = new ArrayList<>();
            switch (quarter) {
                case "Q1":
                    condition.add(year + "-01");
                    condition.add(year + "-02");
                    condition.add(year + "-03");
                    break;
                case "Q2":
                    condition.add(year + "-04");
                    condition.add(year + "-05");
                    condition.add(year + "-06");
                    break;
                case "Q3":
                    condition.add(year + "-07");
                    condition.add(year + "-08");
                    condition.add(year + "-09");
                    break;
                case "Q4":
                    condition.add(year + "-10");
                    condition.add(year + "-11");
                    condition.add(year + "-12");
                    break;
            }
            quarterTraceReports = quarterTraceReportMapper.selectList(
                    new QueryWrapper<QuarterTraceReport>().in("date", condition)
            );
        }
        if (!quarterTraceReports.isEmpty()) {
            for (QuarterTraceReport quarterTraceReport : quarterTraceReports) {
                DateTime date = DateUtil.parse(quarterTraceReport.getDate(), Format.YEAR_MONTH_FORMAT.getValue());
                traceReportListList.add(TraceReportList.builder()
                        .date(quarterTraceReport.getDate())
                        .name("第" + date.quarter() + "季度追溯报告").build());
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
