package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Entrance;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface EntranceMapper extends BaseMapper<Entrance> {

    IPage<Entrance> selectEntranceInfoByCondition(IPage<Entrance> page, @Param("condition") Map<String, Object> condition);

}




