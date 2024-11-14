package edu.hrbu.trace_backend_job.mapper;

import edu.hrbu.trace_backend_job.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend_job.entity.po.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

}
