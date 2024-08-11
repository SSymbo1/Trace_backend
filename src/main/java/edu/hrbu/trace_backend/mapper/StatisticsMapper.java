package edu.hrbu.trace_backend.mapper;

import edu.hrbu.trace_backend.entity.po.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StatisticsMapper {

    int selectMonitorHistogramDataByTimeBetween(@Param("condition") String time);

    int selectMonitorNodeHistogramDataByTimeBetween(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

    Statistics selectStatisticsDataByTimeAndClassId(@Param("before") String before, @Param("now") String now, @Param("id") Integer classId);

}
