package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.dto.analysis.Rank;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EnterpriseMapper extends BaseMapper<Enterprise> {

    List<Enterprise> selectEnterpriseByConditionForMenue(String keyword);

    IPage<Enterprise> selectEnterpriseByCondition(IPage<Enterprise> page, @Param("condition") Map<String, Object> condition);

    Integer selectEnterpriseCount();

    Integer updateRandomEnterpriseAddress(int eid);

    List<Rank> selectProcessEnterpriseSellRankByYearBetween(@Param("start") String start, @Param("end") String end);

}
