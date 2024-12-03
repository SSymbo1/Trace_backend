package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.dto.analysis.EntranceClassCount;
import edu.hrbu.trace_backend_business.entity.dto.analysis.EntranceCount;
import edu.hrbu.trace_backend_business.entity.dto.analysis.Rank;
import edu.hrbu.trace_backend_business.entity.po.Entrance;
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

    List<Rank> selectEntranceSellRankByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    Integer selectEntranceDataCountByDate(@Param("date") String date);

    List<String> selectEntranceAddress();

}




