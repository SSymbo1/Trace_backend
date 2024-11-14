package edu.hrbu.trace_backend_job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_job.entity.po.Supplier;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface SupplierMapper extends BaseMapper<Supplier> {

    IPage<Supplier> selectSupplierByCondition(IPage<Supplier> page, @Param("condition") Map<String, Object> condition);

    IPage<Supplier> selectVendorsByCondition(IPage<Supplier> page, @Param("condition") Map<String, Object> condition);

}




