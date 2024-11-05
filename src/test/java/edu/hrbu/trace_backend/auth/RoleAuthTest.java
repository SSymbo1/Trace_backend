package edu.hrbu.trace_backend.auth;

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
public class RoleAuthTest {

    @Resource
    private RoleMenueContrastMapper roleMenueContrastMapper;
    @Resource
    private MenueMapper menueMapper;

    /***
     * 测试添加所有目录权限给开发角色
     */
    @Test
    public void roleAllMenueTest() {
        List<Menue> menues = menueMapper.selectList(null);
        roleMenueContrastMapper.delete(
                new QueryWrapper<RoleMenueContrast>()
                        .eq("rid", 1)
        );
        for (Menue menue : menues) {
            RoleMenueContrast contrast = RoleMenueContrast.builder()
                    .rid(1)
                    .mid(menue.getMid()).build();
            roleMenueContrastMapper.insert(contrast);
        }

    }

}
