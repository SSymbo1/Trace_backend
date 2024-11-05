package edu.hrbu.trace_backend.mapper;

import edu.hrbu.trace_backend.entity.po.StructReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StructReportMapper extends BaseMapper<StructReport> {
    List<StructReport> selectStructReportList(@Param("date") String date, @Param("type") String type);
}




