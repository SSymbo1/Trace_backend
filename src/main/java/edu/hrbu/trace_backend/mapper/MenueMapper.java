package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.po.Menue;
import edu.hrbu.trace_backend.entity.po.MenueReverse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenueMapper extends BaseMapper<Menue> {

    List<MenueReverse> selectMenueIdReverseBySon(@Param("condition") List<Integer> sonIds);

    List<Integer> selectChildMenueIdByRoleId(@Param("condition") Integer childId);

    List<Menue> selectChildMenueByFatherId(@Param("condition") Integer parent, @Param("current") Integer accountId);
}
