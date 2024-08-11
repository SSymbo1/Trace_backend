package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.monitor.InfoQuery;
import edu.hrbu.trace_backend.entity.dto.monitor.SummaryQuery;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.Classification;
import edu.hrbu.trace_backend.entity.po.MonitorData;
import edu.hrbu.trace_backend.entity.po.Statistics;
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class MonitorServiceImpl implements MonitorService {

    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private ClassificationMapper classificationMapper;

    @Override
    public Result requestHistogramData(SummaryQuery query) {
        List<String> timeRange = timeRangeFormat(query);
        Map<String, Object> data = new HashMap<>();
        List<String> timeData = new ArrayList<>();
        List<Integer> histogramData = new ArrayList<>();
        timeRange.forEach(time -> {
            timeData.add(time);
            histogramData.add(statisticsMapper.selectMonitorHistogramDataByTimeBetween(time));
        });
        data.put("time", timeData);
        data.put("data", histogramData);
        return Result
                .ok(Message.GET_MONITOR_HISTOGRAM_SUCCESS.getValue())
                .data("data", data);
    }

    @Override
    public Result requestSummaryData(SummaryQuery query) {
        int totalData = 0;
        List<String> timeRange = timeRangeFormat(query);
        List<MonitorData> monitorData = new ArrayList<>();
        Map<String, Object> pageData = new HashMap<>();
        timeRange.forEach(time -> {
            MonitorData data = MonitorData.builder()
                    .time(time)
                    .dataNum(statisticsMapper.selectMonitorHistogramDataByTimeBetween(time)).build();
            monitorData.add(data);
        });
        for (MonitorData data : monitorData) {
            totalData += data.getDataNum();
        }
        int startIndex = (query.getCurrentPage() - 1) * query.getPageSize();
        int endIndex = Math.min(startIndex + query.getPageSize(), monitorData.size());
        List<MonitorData> data = monitorData.subList(startIndex, endIndex);
        pageData.put("data", data);
        pageData.put("total", monitorData.size());
        pageData.put("size", query.getPageSize());
        pageData.put("current", query.getCurrentPage());
        pageData.put("pages", monitorData.size() / query.getPageSize() + 1);
        pageData.put("totalData", totalData);
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("iPage", pageData);
    }

    @Override
    public Result requestMonitorData(SummaryQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            query.setNow(currentTime.toString("yyyy-MM-dd"));
            query.setBefore(beforeTime.toString("yyyy-MM-dd"));
        }
        int freshNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 1);
        int processNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 2);
        int drinkNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 3);
        int foodNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 4);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> freshData = new HashMap<>();
        Map<String, Object> processData = new HashMap<>();
        Map<String, Object> drinkData = new HashMap<>();
        Map<String, Object> foodData = new HashMap<>();
        List<Map<String, Object>> statistic = new ArrayList<>();
        data.put("total", freshNode + processNode + drinkNode + foodNode);
        freshData.put("name", "生鲜食品");
        freshData.put("value", freshNode);
        processData.put("name", "加工食品");
        processData.put("value", processNode);
        drinkData.put("name", "饮料与酒水");
        drinkData.put("value", drinkNode);
        foodData.put("name", "食品杂货");
        foodData.put("value", foodNode);
        statistic.add(freshData);
        statistic.add(processData);
        statistic.add(drinkData);
        statistic.add(foodData);
        data.put("data", statistic);
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("data", data);
    }

    @Override
    public Result requestMonitorHistogramData(SummaryQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            query.setNow(currentTime.toString("yyyy-MM-dd"));
            query.setBefore(beforeTime.toString("yyyy-MM-dd"));
        }
        int freshNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 1);
        int processNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 2);
        int drinkNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 3);
        int foodNode = statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 4);
        Map<String, Object> data = new HashMap<>();
        data.put("class", new ArrayList<>(Arrays.asList("生鲜食品", "加工食品", "饮料与酒水", "食品杂货")));
        data.put("data", new ArrayList<>(Arrays.asList(freshNode, processNode, drinkNode, foodNode)));
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("histogram", data);
    }

    @Override
    public Result requestMonitorPieData(SummaryQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            query.setNow(currentTime.toString("yyyy-MM-dd"));
            query.setBefore(beforeTime.toString("yyyy-MM-dd"));
        }
        Map<String, Object> freshNode = new HashMap<>();
        Map<String, Object> processNode = new HashMap<>();
        Map<String, Object> drinkNode = new HashMap<>();
        Map<String, Object> foodNode = new HashMap<>();
        freshNode.put("value", statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 1));
        freshNode.put("name", "生鲜食品");
        processNode.put("value", statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 2));
        processNode.put("name", "加工食品");
        drinkNode.put("value", statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 3));
        drinkNode.put("name", "饮料与酒水");
        foodNode.put("value", statisticsMapper.selectMonitorNodeHistogramDataByTimeBetween(query.getBefore(), query.getNow(), 4));
        foodNode.put("name", "食品杂货");
        List<Map<String, Object>> pieData = new ArrayList<>();
        pieData.add(freshNode);
        pieData.add(processNode);
        pieData.add(drinkNode);
        pieData.add(foodNode);
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("pie", pieData);
    }

    @Override
    public Result requestMonitorInfo(InfoQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            query.setNow(currentTime.toString("yyyy-MM-dd"));
            query.setBefore(beforeTime.toString("yyyy-MM-dd"));
        }
        Integer classId = classificationMapper.selectOne(
                new QueryWrapper<Classification>().eq("name", query.getClassName())
        ).getCid();
        Statistics statistics = statisticsMapper.selectStatisticsDataByTimeAndClassId(query.getBefore(), query.getNow(), classId);
        List<Map<String, Object>> statisticsData = getStatisticsResultMaps(statistics);
        Map<String, Object> data = new HashMap<>();
        data.put("total", statistics.getApproach() + statistics.getEntrance() + statistics.getProcess() + statistics.getSubmit());
        data.put("data", statisticsData);
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("data", data);
    }

    @Override
    public Result requestMonitorInfoPie(InfoQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            query.setNow(currentTime.toString("yyyy-MM-dd"));
            query.setBefore(beforeTime.toString("yyyy-MM-dd"));
        }
        Integer classId = classificationMapper.selectOne(
                new QueryWrapper<Classification>().eq("name", query.getClassName())
        ).getCid();
        Statistics statistics = statisticsMapper.selectStatisticsDataByTimeAndClassId(query.getBefore(), query.getNow(), classId);
        Map<String, Object> data = new HashMap<>();
        List<Map<String, Object>> statisticsData = getStatisticsResultMaps(statistics);
        data.put("statistics", statisticsData);
        return Result
                .ok(Message.GET_MONITOR_SUCCESS.getValue())
                .data("data", data);
    }

    private static List<Map<String, Object>> getStatisticsResultMaps(Statistics statistics) {
        List<Map<String, Object>> statisticsData = new ArrayList<>();
        Map<String, Object> process = new HashMap<>();
        Map<String, Object> submit = new HashMap<>();
        Map<String, Object> approach = new HashMap<>();
        Map<String, Object> entrance = new HashMap<>();
        process.put("name", "产品备案");
        process.put("value", statistics.getProcess());
        submit.put("name", "产品提交备案");
        submit.put("value", statistics.getSubmit());
        approach.put("name", "超市进场");
        approach.put("value", statistics.getApproach());
        entrance.put("name", "超市出场");
        entrance.put("value", statistics.getEntrance());
        statisticsData.add(process);
        statisticsData.add(submit);
        statisticsData.add(approach);
        statisticsData.add(entrance);
        return statisticsData;
    }

    private List<String> timeRangeFormat(SummaryQuery query) {
        if (query.getBefore() == null || query.getNow() == null) {
            DateTime currentTime = new DateTime(DateTime.now());
            DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
            currentTime = new DateTime(DateTime.now());
            List<DateTime> range = DateUtil.rangeToList(
                    beforeTime,
                    currentTime,
                    DateField.DAY_OF_YEAR
            );
            List<String> timeRange = new ArrayList<>();
            range.forEach(time -> timeRange.add(time.toString("yyyy-MM-dd")));
            return timeRange;
        }
        List<DateTime> range = DateUtil.rangeToList(
                DateUtil.parse(query.getBefore()),
                DateUtil.parse(query.getNow()),
                DateField.DAY_OF_YEAR
        );
        List<String> timeRange = new ArrayList<>();
        range.forEach(time -> timeRange.add(time.toString("yyyy-MM-dd")));
        return timeRange;
    }

}
