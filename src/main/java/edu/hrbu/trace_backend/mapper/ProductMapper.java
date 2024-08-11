package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> selectProductByCondition(IPage<Product> page, @Param("condition") Map<String, Object> condition);

}




