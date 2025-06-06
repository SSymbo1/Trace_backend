package edu.hrbu.trace_backend_business.mapper;

import edu.hrbu.trace_backend_business.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend_business.entity.po.Statistics;
import edu.hrbu.trace_backend_business.entity.po.json.TraceDataCollect;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StatisticsMapper {

    Integer selectMonitorHistogramDataByTimeBetween(@Param("condition") String time);

    StructData selectStructTotalStatisticsData(@Param("date") String date, @Param("eid") Integer eid, @Param("type") String type);

    Integer selectMonitorNodeHistogramDataByTimeBetween(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

    Statistics selectStatisticsDataByTimeAndClassId(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

    Integer selectReportStatisticsCountByCondition(@Param("date") String date, @Param("condition") String condition, @Param("type") Integer type, @Param("eid") Integer eid);

    Integer selectReportStatisticsYOYByCondition(@Param("date") String date, @Param("condition") String condition, @Param("type") Integer type, @Param("eid") Integer eid);

    Integer selectReportStatisticsQOQByCondition(@Param("date") String date, @Param("condition") String condition, @Param("type") Integer type, @Param("eid") Integer eid);

    Integer selectTraceDataCount();

    Integer selectEnterpriseTypeCount(@Param("type") Integer type);

    TraceDataCollect selectTraceDataCollect(@Param("date") String date, @Param("type") Integer type, @Param("condition") String condition, @Param("time") String time);

    List<String> selectTraceAddressByDateAndType(@Param("date") String date, @Param("type") String type);

    String selectTracePopularEnterpriseNameByCondition(@Param("date") String date, @Param("type") Integer type, @Param("condition") String condition, @Param("time") String time);

    TraceDataCollect selectTraceDataCollectQuarter(@Param("start") String start, @Param("end") String end, @Param("type") Integer type, @Param("condition") String condition);

    String selectTracePopularEnterpriseNameByConditionQuarter(@Param("start") String start, @Param("end") String end, @Param("type") Integer type, @Param("condition") String condition);

    Integer selectReportStatisticsYOYByConditionQuarter(@Param("start") String start, @Param("end") String end, @Param("type") Integer type, @Param("eid") Integer eid);

    Integer selectReportStatisticsQOQByConditionQuarter(@Param("start") String start, @Param("end") String end, @Param("type") Integer type, @Param("eid") Integer eid);

    StructData selectStructTotalStatisticsDataQuarter(@Param("start") String start, @Param("end") String end, @Param("eid") Integer eid);

    List<String> selectTraceAddressByDateAndTypeQuarter(@Param("start") String start, @Param("end") String end);

}
