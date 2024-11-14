package edu.hrbu.trace_backend_job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_job.entity.OnlineContext;
import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.enums.Table;
import edu.hrbu.trace_backend_job.entity.po.*;
import edu.hrbu.trace_backend_job.entity.enums.Message;
import edu.hrbu.trace_backend_job.mapper.*;
import edu.hrbu.trace_backend_job.service.MenueService;
import edu.hrbu.trace_backend_job.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ClassificationMapper classificationMapper;

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

    @Override
    public Result requestRoleSubMenue() {
        QueryWrapper<Role> roleSubWrapper = new QueryWrapper<>();
        roleSubWrapper.eq("del", 0);
        List<Role> roleSubMenue = roleMapper.selectList(roleSubWrapper);
        return Result
                .ok(Message.GET_ROLE_SUB_SUCCESS.getValue())
                .data("menue", roleSubMenue);
    }

    @Override
    public Result requestEnterpriseMenue(String keyword) {
        return Result
                .ok(Message.GET_ENTERPRISE_SUB_SUCCESS.getValue())
                .data("menue", enterpriseMapper.selectEnterpriseByConditionForMenue(keyword));
    }

    @Override
    public Result requestRoleTreeMenue() {
        List<Menue> menueList = menueMapper.selectList(null);
        List<Menue> treeList = new ArrayList<>();
        menueList.forEach(menue -> {
            if (menue.getParent().equals(0)) {
                Menue base = Menue.builder()
                        .value(menue.getMid())
                        .label(menue.getName())
                        .children(new ArrayList<>()).build();
                treeList.add(base);
            } else {
                treeList.forEach(children -> {
                    if (children.getValue().equals(menue.getParent())) {
                        Menue child = Menue.builder()
                                .value(menue.getMid())
                                .label(menue.getName())
                                .children(new ArrayList<>()).build();
                        children.getChildren().add(child);
                    } else {
                        children.getChildren().forEach(son -> {
                            if (son.getValue().equals(menue.getParent())) {
                                Menue sonChild = Menue.builder()
                                        .value(menue.getMid())
                                        .label(menue.getName()).build();
                                son.getChildren().add(sonChild);
                            }
                        });
                    }
                });
            }
        });
        return Result
                .ok(Message.GET_TREE_ROLE_MENUE.getValue())
                .data("tree", treeList);
    }

    @Override
    public Result requestClassificationTreeMenue() {
        List<Classification> menueList = classificationMapper.selectList(null);
        List<Classification> treeList = new ArrayList<>();
        menueList.forEach(menue -> {
            if (menue.getParent().equals(0)) {
                Classification base = Classification.builder()
                        .value(menue.getCid())
                        .label(menue.getName())
                        .children(new ArrayList<>()).build();
                treeList.add(base);
            } else {
                treeList.forEach(children -> {
                    if (children.getValue().equals(menue.getParent())) {
                        Classification child = Classification.builder()
                                .value(menue.getCid())
                                .label(menue.getName()).build();
                        children.getChildren().add(child);
                    }
                });
            }
        });
        return Result
                .ok(Message.GET_TREE_CLASSIFICATION_MENUE.getValue())
                .data("tree", treeList);
    }

    private List<Menue> subMenueSelector(Integer target) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        List<Menue> baseMenue = menueMapper.selectChildMenueByFatherId(target, currentAccountId);
        if (!baseMenue.isEmpty()) {
            baseMenue.forEach(menue -> {
                menue.setChildren(menueMapper.selectChildMenueByFatherId(menue.getMid(), currentAccountId));
            });
        }
        return baseMenue;
    }
}
