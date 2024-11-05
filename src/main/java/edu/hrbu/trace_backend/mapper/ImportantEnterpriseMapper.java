package edu.hrbu.trace_backend.mapper;

import edu.hrbu.trace_backend.entity.po.ImportantEnterprise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImportantEnterpriseMapper extends BaseMapper<ImportantEnterprise> {

    List<ImportantEnterprise> selectImportantList(@Param("type") Integer type);

}




