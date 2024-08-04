package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Supplier;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    IPage<Supplier> selectSupplierByCondition(IPage<Supplier> page, @Param("condition") Map<String, Object> condition);

    IPage<Supplier> selectVendorsByCondition(IPage<Supplier> page, @Param("condition") Map<String, Object> condition);

}




