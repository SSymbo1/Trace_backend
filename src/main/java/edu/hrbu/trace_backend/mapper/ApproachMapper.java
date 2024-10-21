package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Approach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.po.ApproachClassCount;
import edu.hrbu.trace_backend.entity.po.ApproachCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ApproachMapper extends BaseMapper<Approach> {

    IPage<Approach> selectApproachInfoByCondition(IPage<Approach> page, @Param("condition") Map<String, Object> condition);

    List<Approach> selectApproachInfoByYearBetween(@Param("start") String start, @Param("end") String end);

    List<ApproachClassCount> selectAnalysisClassCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<ApproachCount> selectAnalysisApproachCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<ApproachCount> selectAnalysisApproachMonthCountByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<ApproachClassCount> selectAnalysisClassCountMonthByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

    List<Approach> selectAnalysisApproachInfoByYearBetween(@Param("start") String start, @Param("end") String end, @Param("type") Integer type);

}




