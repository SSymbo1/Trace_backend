package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.po.Product;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    IPage<Product> selectProductByCondition(IPage<Product> page, @Param("condition") Map<String, Object> condition);

    @MapKey("name")
    Map<String, Object> selectDataCollectFromCount(@Param("condition") String condition);

    List<String> selectProductEnterpriseAddress();

}




