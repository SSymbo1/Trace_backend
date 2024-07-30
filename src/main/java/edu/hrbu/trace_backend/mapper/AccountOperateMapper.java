package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.AccountOperate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountOperateMapper extends BaseMapper<AccountOperate> {

    IPage<AccountOperate> selectAccountOperateByCondition(IPage<AccountOperate> page, @Param("condition") String condition);

}




