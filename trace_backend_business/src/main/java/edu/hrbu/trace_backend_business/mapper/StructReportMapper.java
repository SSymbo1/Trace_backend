package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend_business.entity.po.StructReport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StructReportMapper extends BaseMapper<StructReport> {

    List<StructReport> selectStructReportList(@Param("date") String date, @Param("type") String type);

    Integer selectStructReportCount(@Param("date") String date, @Param("type") String type);

}




