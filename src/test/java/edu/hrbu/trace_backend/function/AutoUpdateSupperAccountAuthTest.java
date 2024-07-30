package edu.hrbu.trace_backend.function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.po.Menue;
import edu.hrbu.trace_backend.entity.po.RoleMenueContrast;
import edu.hrbu.trace_backend.mapper.MenueMapper;
import edu.hrbu.trace_backend.mapper.RoleMenueContrastMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest
public class AutoUpdateSupperAccountAuthTest {

    @Resource
    private RoleMenueContrastMapper roleMenueContrastMapper;
    @Resource
    private MenueMapper menueMapper;

    @Test
    public void updateSupperAccountAuth() {
        QueryWrapper<RoleMenueContrast> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("rid", 1);
        roleMenueContrastMapper.delete(queryWrapper);
        List<Menue> menues = menueMapper.selectList(null);
        menues.forEach(menu -> {
            RoleMenueContrast updateAuth = RoleMenueContrast.builder()
                    .rid(1)
                    .mid(menu.getMid()).build();
            roleMenueContrastMapper.insert(updateAuth);
        });
    }
}
