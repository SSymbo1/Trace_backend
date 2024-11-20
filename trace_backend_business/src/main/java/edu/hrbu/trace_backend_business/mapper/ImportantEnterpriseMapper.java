package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend_business.entity.po.ImportantEnterprise;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImportantEnterpriseMapper extends BaseMapper<ImportantEnterprise> {

    List<ImportantEnterprise> selectImportantList(@Param("type") Integer type);

}




