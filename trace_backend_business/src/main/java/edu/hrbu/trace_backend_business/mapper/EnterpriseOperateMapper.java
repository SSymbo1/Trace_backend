package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.po.EnterpriseOperate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EnterpriseOperateMapper extends BaseMapper<EnterpriseOperate> {

    IPage<EnterpriseOperate> selectEnterpriseOperateByCondition(IPage<EnterpriseOperate> page, @Param("condition") String condition);

}




