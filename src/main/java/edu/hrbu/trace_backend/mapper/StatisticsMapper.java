package edu.hrbu.trace_backend.mapper;

import edu.hrbu.trace_backend.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend.entity.po.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticsMapper {

    Integer selectMonitorHistogramDataByTimeBetween(@Param("condition") String time);

    StructData selectStructTotalStatisticsData(@Param("date") String date, @Param("eid") Integer eid, @Param("type") String type);

    Integer selectMonitorNodeHistogramDataByTimeBetween(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

    Statistics selectStatisticsDataByTimeAndClassId(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

    Integer selectTraceDataCount();

}
