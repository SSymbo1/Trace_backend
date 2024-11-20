package edu.hrbu.trace_backend_business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend_business.entity.po.RoleOperate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleOperateMapper extends BaseMapper<RoleOperate> {

    IPage<RoleOperate> selectRoleOperateByCondition(IPage<RoleOperate> page, @Param("condition") String condition);

}




