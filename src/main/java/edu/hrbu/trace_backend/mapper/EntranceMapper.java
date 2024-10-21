package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Entrance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.po.EntranceClassCount;
import edu.hrbu.trace_backend.entity.po.EntranceCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EntranceMapper extends BaseMapper<Entrance> {

    IPage<Entrance> selectEntranceInfoByCondition(IPage<Entrance> page, @Param("condition") Map<String, Object> condition);

    Integer selectTraceDataExistByCondition(@Param("name") String name, @Param("code") String code, @Param("trace") String trace);

    List<Entrance> selectEntranceInfoByYearBetween(@Param("start") String start, @Param("end") String end);

    List<Entrance> selectAnalysisEntranceInfoByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<Entrance> selectAnalysisEntranceInfoMonthByYearBetween(@Param("time") String time, @Param("type") Integer type);

    List<EntranceClassCount> selectAnalysisClassCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<EntranceCount> selectAnalysisEntranceCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<EntranceCount> selectAnalysisEntranceMonthCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<EntranceClassCount> selectAnalysisClassCountMonthByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

}




