package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.RoleOperate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface RoleOperateMapper extends BaseMapper<RoleOperate> {

    IPage<RoleOperate> selectRoleOperateByCondition(IPage<RoleOperate> page,@Param("condition") String condition);

}




