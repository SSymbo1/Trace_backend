package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Table;
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
                .data("homeMenu", menueMapper.selectList(menuWrapper));
    }

    @Override
    public Result requestAnalysisMenue() {
        return Result
                .ok(Message.GET_ANALYSIS_MENUE_SUCCESS.getValue())
                .data("menue", subMenueSelector(Table.MENUE_ANALYSIS.getValue()));
    }

    @Override
    public Result requestMonitorMenue() {
        return Result
                .ok(Message.GET_MONITOR_MENUE_SUCCESS.getValue())
                .data("menue", subMenueSelector(Table.MENUE_MONITOR.getValue()));
    }

    @Override
    public Result requestSegmentMenue() {
        return Result
                .ok(Message.GET_SEGMENT_MENUE_SUCCESS.getValue())
                .data("menue", subMenueSelector(Table.MENUE_SEGMENT.getValue()));
    }

    @Override
    public Result requestSubjectMenue() {
        return Result
                .ok(Message.GET_SUBJECT_MENUE_SUCCESS.getValue())
                .data("menue", subMenueSelector(Table.MENUE_SUBJECT.getValue()));
    }

    @Override
    public Result requestSystemMenue() {
        return Result
                .ok(Message.GET_SUBJECT_MENUE_SUCCESS.getValue())
                .data("menue", subMenueSelector(Table.MENUE_SYSTEM.getValue()));
    }

    private List<Menue> subMenueSelector(Integer target) {
        QueryWrapper<Menue> baseWrapper = new QueryWrapper<>();
        baseWrapper
                .eq("mid", target)
                .and(condition -> condition.eq("del", 0));
        Menue baseTree = menueMapper.selectOne(baseWrapper);
        QueryWrapper<Menue> treeWrapper = new QueryWrapper<>();
        treeWrapper
                .eq("parent", baseTree.getMid())
                .and(condition -> condition.eq("del", 0));
        List<Menue> subMenue = menueMapper.selectList(treeWrapper);
        if (!subMenue.isEmpty()) {
            subMenue.forEach(menue -> {
                QueryWrapper<Menue> childrenWrapper = new QueryWrapper<>();
                childrenWrapper
                        .eq("parent", menue.getMid())
                        .and(condition -> condition.eq("del", 0));
                List<Menue> childrenTree = menueMapper.selectList(childrenWrapper);
                menue.setChildren(childrenTree);
            });
        }
        return subMenue;
    }

}
