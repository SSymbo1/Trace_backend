package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.po.Product;
import edu.hrbu.trace_backend_business.entity.po.ProductRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductRecordMapper extends BaseMapper<ProductRecord> {

    IPage<Product> selectProductRecordProcessByCondition(IPage<Product> page, @Param("condition") Map<String, Object> condition);

    Integer selectProductRecordByCondition(@Param("productName") String name, @Param("productCode") String code);

    Integer selectExistProductRecordByCondition(@Param("enterprise") String enterprise, @Param("code") String code, @Param("name") String name);

    Integer selectProductRecordCount();

    List<ProductRecord> selectProcessedInfoByYearBetween(@Param("start") String start, @Param("end") String end);

    List<ProductRecord> selectInsertInfoByYearBetween(@Param("start") String start, @Param("end") String end);

}
