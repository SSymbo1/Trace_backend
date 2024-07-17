package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.po.Account;
import edu.hrbu.trace_backend.entity.po.Menue;
import edu.hrbu.trace_backend.entity.po.RoleMenueContrast;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.mapper.AccountMapper;
import edu.hrbu.trace_backend.mapper.MenueMapper;
import edu.hrbu.trace_backend.mapper.RoleMenueContrastMapper;
import edu.hrbu.trace_backend.service.MenueService;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class MenueServiceImpl implements MenueService {

    @Resource
    private AccountMapper accountMapper;
    @Resource
    private MenueMapper menueMapper;
    @Resource
    private RoleMenueContrastMapper roleMenueContrastMapper;

    @Override
    public Result requestHomeMenue() {
        String aid = JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject();
        QueryWrapper<Account> whatUserWrapper = new QueryWrapper<>();
        whatUserWrapper.eq("aid", aid);
        Account account = accountMapper.selectOne(whatUserWrapper);
        QueryWrapper<RoleMenueContrast> roleHasMenueWrapper = new QueryWrapper<>();
        roleHasMenueWrapper.eq("rid", account.getRid());
        List<RoleMenueContrast> roleHasMenue = roleMenueContrastMapper.selectList(roleHasMenueWrapper);
        Set<Integer> midSet = new HashSet<>();
        roleHasMenue.forEach(role -> midSet.add(role.getMid()));
        QueryWrapper<Menue> menuWrapper = new QueryWrapper<>();
        menuWrapper
                .in("mid", midSet)
                .and(
                        condition -> condition
                                .eq("del", 0)
                                .and(parent -> parent.eq("parent", 0))
                );
        return Result
                .ok(Message.GET_HOME_MENUE_SUCCESS.getValue())
                .data("homeMenu",menueMapper.selectList(menuWrapper));
    }

    @Override
    public Result requestAnalysisMenue() {
        return null;
    }

    @Override
    public Result requestConstructMenue() {
        return null;
    }

    @Override
    public Result requestDeviceMenue() {
        return null;
    }

    @Override
    public Result requestEmergencyMenue() {
        return null;
    }

    @Override
    public Result requestMonitorMenue() {
        return null;
    }

    @Override
    public Result requestOperationMenue() {
        return null;
    }

    @Override
    public Result requestQualityMenue() {
        return null;
    }

    @Override
    public Result requestSegmentMenue() {
        return null;
    }

    @Override
    public Result requestSubjectMenue() {
        return null;
    }

    @Override
    public Result requestSystemMenue() {
        return null;
    }
}
