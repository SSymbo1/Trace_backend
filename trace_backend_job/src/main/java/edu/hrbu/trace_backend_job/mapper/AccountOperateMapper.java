package edu.hrbu.trace_backend_job.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_job.entity.po.AccountOperate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountOperateMapper extends BaseMapper<AccountOperate> {

    IPage<AccountOperate> selectAccountOperateByCondition(IPage<AccountOperate> page, @Param("condition") String condition);

}




