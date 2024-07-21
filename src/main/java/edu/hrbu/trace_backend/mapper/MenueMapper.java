package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.po.Menue;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenueMapper extends BaseMapper<Menue> {
}
