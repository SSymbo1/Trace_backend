package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.Product;
import edu.hrbu.trace_backend.entity.po.ProductRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface ProductRecordMapper extends BaseMapper<ProductRecord> {

    IPage<Product> selectProductRecordProcessByCondition(IPage<Product> page, @Param("condition") Map<String, Object> condition);

    int selectProductRecordByCondition(@Param("productName") String name, @Param("productCode") String code);

}
