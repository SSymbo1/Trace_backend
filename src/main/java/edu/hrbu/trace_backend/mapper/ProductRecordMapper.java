package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.po.ProductRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductRecordMapper extends BaseMapper<ProductRecord> {
}
