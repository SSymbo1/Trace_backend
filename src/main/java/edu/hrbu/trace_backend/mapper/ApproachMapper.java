package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Approach;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ApproachMapper extends BaseMapper<Approach> {

    IPage<Approach> selectApproachInfoByCondition(IPage<Approach> page, @Param("condition") Map<String, Object> condition);

}




