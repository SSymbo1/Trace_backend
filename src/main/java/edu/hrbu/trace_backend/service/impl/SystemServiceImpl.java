package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.system.*;
import edu.hrbu.trace_backend.entity.dto.system.Account;
import edu.hrbu.trace_backend.entity.dto.system.Role;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.service.SystemService;
import edu.hrbu.trace_backend.util.AesUtil;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountOperateMapper accountOperateMapper;
    @Resource
    private EnterpriseOperateMapper enterpriseOperateMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenueMapper menueMapper;
    @Resource
    private RoleMenueContrastMapper roleMenueContrastMapper;
    @Resource
    private RoleOperateMapper roleOperateMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private SupplierMapper supplierMapper;
    @Value("${resources.avatar}")
    private String avatarPath;

    @Override
    public Result requestAccountInfoPaged(UserQuery query) {
        IPage<AccountInfo> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getAccountInfoSearchCondition(query);
        IPage<AccountInfo> accountInfoIPage = accountInfoMapper.selectAccountInfoByCondition(page, condition);
        if (!accountInfoIPage.getRecords().isEmpty()) {
            accountInfoIPage.getRecords().forEach(record -> record.setAvatar(avatarPath + record.getAvatar()));
        }
        return Result
                .ok(Message.GET_ACCOUNT_INFO_SUCCESS.getValue())
                .data("iPage", accountInfoIPage);
    }

    @Override
    public Result requestAccountAdd(Account account) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> existAccountWrapper = new QueryWrapper<>();
        existAccountWrapper.eq("username", account.getUsername());
        edu.hrbu.trace_backend.entity.po.Role role = roleMapper.selectById(account.getRole());
        if (role.getBan().equals(1)) {
            return Result
                    .fail(Message.CANNOT_EDIT_BAN_ROLE_ACCOUNT.getValue())
                    .data("createAid", currentAccountId);
        }
        if (accountMapper.selectOne(existAccountWrapper) != null) {
            return Result
                    .fail(Message.ACCOUNT_USER_EXIST.getValue())
                    .data("createAid", currentAccountId);
        }
        edu.hrbu.trace_backend.entity.po.Account insertAccount = edu.hrbu.trace_backend.entity.po.Account.builder()
                .username(account.getUsername())
                .password(AesUtil.encryptHex(account.getPassword()))
                .rid(account.getRole())
                .build();
        int insert = accountMapper.insert(insertAccount);
        if (insert <= 0) {
            return Result
                    .fail(Message.ADD_ACCOUNT_FAIL.getValue())
                    .data("createAid", currentAccountId);
        }
        AccountInfo insertAccountInfo = AccountInfo.builder()
                .aid(insertAccount.getAid())
                .eid(account.getEnterprise())
                .rid(account.getRole())
                .name(account.getName())
                .gander(account.getGander())
                .tel(account.getTel())
                .avatar("".equals(account.getAvatar()) || account.getAvatar() == null ? "default.png" : account.getAvatar())
                .email(account.getEmail())
                .address(account.getAddress())
                .zipCode(account.getZipCode()).build();
        int insertInfo = accountInfoMapper.insert(insertAccountInfo);
        if (insertInfo <= 0) {
            return Result
                    .fail(Message.ADD_ACCOUNT_FAIL.getValue())
                    .data("createAid", currentAccountId);
        }
        return Result
                .ok(Message.ADD_ACCOUNT_SUCCESS.getValue())
                .data("createAid", insertAccount.getAid());
    }

    @Override
    public Result requestAccountStatueSet(AccountStatue statue) {
        edu.hrbu.trace_backend.entity.po.Account updateAccountStatue = null;
        if (statue.getDel().equals(1)) {
            updateAccountStatue = edu.hrbu.trace_backend.entity.po.Account.builder()
                    .aid(statue.getAid())
                    .del(statue.getDel())
                    .ban(1).build();
        } else {
            updateAccountStatue = edu.hrbu.trace_backend.entity.po.Account.builder()
                    .aid(statue.getAid())
                    .del(statue.getDel())
                    .ban(statue.getBan()).build();
        }
        if (statue.getBan().equals(0)) {
            edu.hrbu.trace_backend.entity.po.Account account = accountMapper.selectById(statue.getAid());
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Role> roleQueryWrapper = new QueryWrapper<>();
            roleQueryWrapper.eq("rid", account.getRid());
            if (roleMapper.selectOne(roleQueryWrapper).getBan().equals(1)) {
                return Result.fail(Message.CANNOT_SET_BAN_ROLE_ACCOUNT.getValue());
            }
        }
        int updateStatue = accountMapper.updateById(updateAccountStatue);
        if (updateStatue <= 0) {
            return Result.fail(Message.SET_ACCOUNT_STATUE_FAIL.getValue());
        }
        return Result.ok(Message.SET_ACCOUNT_STATUE_SUCCESS.getValue());
    }

    @Override
    public Result requestAccountEdit(Account account) {
        edu.hrbu.trace_backend.entity.po.Role role = roleMapper.selectById(account.getRole());
        if (role.getBan().equals(1)) {
            return Result
                    .fail(Message.CANNOT_EDIT_BAN_ROLE_ACCOUNT.getValue())
                    .data("createAid", account.getAid());
        }
        edu.hrbu.trace_backend.entity.po.Account updateAccount = edu.hrbu.trace_backend.entity.po.Account.builder()
                .aid(account.getAid())
                .username(account.getUsername())
                .password(AesUtil.encryptHex(account.getPassword()))
                .rid(account.getRole()).build();
        AccountInfo updateAccountInfo = AccountInfo.builder()
                .aid(account.getAid())
                .eid(account.getEnterprise())
                .rid(account.getRole())
                .name(account.getName())
                .gander(account.getGander())
                .tel(account.getTel())
                .avatar("".equals(account.getAvatar()) || account.getAvatar() == null ? "default.png" : account.getAvatar())
                .email(account.getEmail())
                .address(account.getAddress())
                .zipCode(account.getZipCode()).build();
        edu.hrbu.trace_backend.entity.po.Account exist = accountMapper.selectById(account.getAid());
        if (exist.getUsername().equals(account.getUsername())) {
            int updateAccountRes = accountMapper.updateById(updateAccount);
            int updateAccountInfoRes = accountInfoMapper.updateById(updateAccountInfo);
            return updateAccountRes > 0 && updateAccountInfoRes > 0 ?
                    Result
                            .ok(Message.EDIT_ACCOUNT_SUCCESS.getValue())
                            .data("createAid", account.getAid())
                    :
                    Result
                            .fail(Message.EDIT_ACCOUNT_FAIL.getValue())
                            .data("createAid", account.getAid());
        } else {
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> existWrapper = new QueryWrapper<>();
            existWrapper.eq("username", account.getUsername());
            if (!accountMapper.selectList(existWrapper).isEmpty()) {
                return Result
                        .fail(Message.ACCOUNT_USER_EXIST.getValue())
                        .data("createAid", account.getAid());
            }
            int updateAccountRes = accountMapper.updateById(updateAccount);
            int updateAccountInfoRes = accountInfoMapper.updateById(updateAccountInfo);
            return updateAccountRes > 0 && updateAccountInfoRes > 0 ?
                    Result
                            .ok(Message.EDIT_ACCOUNT_SUCCESS.getValue())
                            .data("createAid", account.getAid())
                    :
                    Result
                            .fail(Message.EDIT_ACCOUNT_FAIL.getValue()).
                            data("createAid", account.getAid());
        }
    }

    @Override
    public Result requestDisableAllAccount(Able able) {
        if (!able.getVerify().equalsIgnoreCase(able.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - able.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("rid", 1).
                and(ban -> ban.ne("ban", 1));
        edu.hrbu.trace_backend.entity.po.Account updateBan = edu.hrbu.trace_backend.entity.po.Account.builder()
                .ban(1).build();
        accountMapper.update(updateBan, queryWrapper);
        return Result.ok(Message.DISABLE_ALL_ACCOUNT.getValue());
    }

    @Override
    public Result requestEnableAllAccount(Able able) {
        if (!able.getVerify().equalsIgnoreCase(able.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - able.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("rid", 1)
                .and(ban -> ban.ne("ban", 0))
                .and(del -> del.ne("del", 1));
        List<edu.hrbu.trace_backend.entity.po.Account> accounts = accountMapper.selectList(queryWrapper);
        if (!accounts.isEmpty()) {
            for (edu.hrbu.trace_backend.entity.po.Account account : accounts) {
                edu.hrbu.trace_backend.entity.po.Role role = roleMapper.selectById(account.getRid());
                if (role.getBan().equals(1)) {
                    return Result.fail(Message.CANNOT_ENABLE_ALL_ACCOUNT.getValue());
                }
            }
        }
        edu.hrbu.trace_backend.entity.po.Account updateBan = edu.hrbu.trace_backend.entity.po.Account.builder()
                .ban(0).build();
        accountMapper.update(updateBan, queryWrapper);
        return Result.ok(Message.ENABLE_ALL_ACCOUNT.getValue());
    }

    @Override
    public Result requestEnterpriseInfoPaged(EnterpriseQuery query) {
        IPage<edu.hrbu.trace_backend.entity.po.Enterprise> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getEnterpriseInfoSearchCondition(query);
        return Result
                .ok(Message.GET_ENTERPRISE_SUCCESS.getValue())
                .data("iPage", enterpriseMapper.selectEnterpriseByCondition(page, condition));
    }

    @Override
    public Result requestEnterpriseAdd(edu.hrbu.trace_backend.entity.dto.system.Enterprise enterprise) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Enterprise> existWrapper = new QueryWrapper<>();
        existWrapper.eq("name", enterprise.getName());
        if (enterpriseMapper.selectOne(existWrapper) != null) {
            return Result
                    .fail(Message.ADD_ENTERPRISE_EXIST.getValue())
                    .data("createAid", currentAccountId);
        }
        edu.hrbu.trace_backend.entity.po.Enterprise insertEnterprise = edu.hrbu.trace_backend.entity.po.Enterprise.builder()
                .name(enterprise.getName())
                .legalPerson(enterprise.getLegalPerson())
                .tel(enterprise.getTel())
                .socialCode(enterprise.getSocialCode())
                .address(enterprise.getAddress())
                .zipCode(enterprise.getZipCode()).build();
        int insertStatue = enterpriseMapper.insert(insertEnterprise);
        if (insertStatue <= 0) {
            return Result
                    .fail(Message.ADD_ENTERPRISE_FAIL.getValue())
                    .data("createAid", currentAccountId);
        }
        Supplier insertSupplier = Supplier.builder()
                .eid(insertEnterprise.getEid())
                .code(ObjectId.next())
                .type(enterprise.getType()).build();
        supplierMapper.insert(insertSupplier);
        return Result
                .ok(Message.ADD_ENTERPRISE_SUCCESS.getValue())
                .data("createAid", insertEnterprise.getEid());
    }

    @Override
    public Result requestEnterpriseEdit(edu.hrbu.trace_backend.entity.dto.system.Enterprise enterprise) {
        edu.hrbu.trace_backend.entity.po.Enterprise updateEnterprise = edu.hrbu.trace_backend.entity.po.Enterprise.builder()
                .eid(enterprise.getEid())
                .name(enterprise.getName())
                .tel(enterprise.getTel())
                .legalPerson(enterprise.getLegalPerson())
                .socialCode(enterprise.getSocialCode())
                .address(enterprise.getAddress())
                .zipCode(enterprise.getZipCode()).build();
        edu.hrbu.trace_backend.entity.po.Enterprise exist = enterpriseMapper.selectById(enterprise.getEid());
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        supplierQueryWrapper.eq("eid", enterprise.getEid());
        Supplier updateSupplier = Supplier.builder()
                .type(enterprise.getType()).build();
        if (exist.getName().equals(enterprise.getName())) {
            int updateStatue = enterpriseMapper.updateById(updateEnterprise);
            supplierMapper.update(updateSupplier, supplierQueryWrapper);
            return updateStatue > 0 ?
                    Result
                            .ok(Message.EDIT_ENTERPRISE_SUCCESS.getValue())
                            .data("createAid", enterprise.getEid())
                    :
                    Result
                            .fail(Message.EDIT_ENTERPRISE_FAIL.getValue())
                            .data("createAid", enterprise.getEid());
        } else {
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Enterprise> existWrapper = new QueryWrapper<>();
            existWrapper.eq("name", enterprise.getName());
            if (!enterpriseMapper.selectList(existWrapper).isEmpty()) {
                return Result
                        .fail(Message.ADD_ENTERPRISE_EXIST.getValue())
                        .data("createAid", enterprise.getEid());
            }
            int updateStatue = enterpriseMapper.updateById(updateEnterprise);
            supplierMapper.update(updateSupplier, supplierQueryWrapper);
            return updateStatue > 0 ?
                    Result
                            .ok(Message.EDIT_ENTERPRISE_SUCCESS.getValue())
                            .data("createAid", enterprise.getEid())
                    :
                    Result
                            .fail(Message.EDIT_ENTERPRISE_FAIL.getValue())
                            .data("createAid", enterprise.getEid());
        }
    }

    @Override
    public Result requestRoleStatueSet(RoleStatue statue) {
        edu.hrbu.trace_backend.entity.po.Role updateRoleStatue = null;
        if (statue.getDel().equals(1)) {
            updateRoleStatue = edu.hrbu.trace_backend.entity.po.Role.builder()
                    .rid(statue.getRid())
                    .del(statue.getDel())
                    .ban(1).build();
        } else {
            updateRoleStatue = edu.hrbu.trace_backend.entity.po.Role.builder()
                    .rid(statue.getRid())
                    .del(statue.getDel())
                    .ban(statue.getBan()).build();
        }
        if (updateRoleStatue.getBan().equals(1)) {
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> accountQueryWrapper = new QueryWrapper<>();
            accountQueryWrapper.eq("rid", updateRoleStatue.getRid());
            List<edu.hrbu.trace_backend.entity.po.Account> accounts = accountMapper.selectList(accountQueryWrapper);
            if (!accounts.isEmpty()) {
                accounts.forEach(account -> {
                    edu.hrbu.trace_backend.entity.po.Account updateAccount = edu.hrbu.trace_backend.entity.po.Account.builder()
                            .aid(account.getAid())
                            .ban(1).build();
                    accountMapper.updateById(updateAccount);
                });
            }
        }
        int updateStatue = roleMapper.updateById(updateRoleStatue);
        if (updateStatue <= 0) {
            return Result.fail(Message.SET_ROLE_STATUE_FAIL.getValue());
        }
        return Result.ok(Message.SET_ROLE_STATUE_SUCCESS.getValue());
    }

    @Override
    public Result requestRoleInfoPaged(RoleQuery query) {
        IPage<edu.hrbu.trace_backend.entity.po.Role> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getRoleInfoSearchCondition(query);
        IPage<edu.hrbu.trace_backend.entity.po.Role> data = roleMapper.selectRoleByCondition(page, condition);
        data.getRecords().forEach(record -> {
            record.setMenueList(Arrays.asList(record.getMenues().split(",")));
        });
        return Result
                .ok(Message.GET_ROLE_SUCCESS.getValue())
                .data("iPage", data);
    }

    @Override
    public Result requestRoleEdit(Role role) {
        List<Integer> menues = role.getMenues();
        Set<Integer> midSet = new HashSet<>(menues);
        List<MenueReverse> reverseList = menueMapper.selectMenueIdReverseBySon(menues);
        reverseList.forEach(reverse -> {
            midSet.add(reverse.getParent());
            midSet.add(reverse.getFather());
        });
        QueryWrapper<RoleMenueContrast> deleterContrastWrapper = new QueryWrapper<>();
        deleterContrastWrapper.eq("rid", role.getRid());
        edu.hrbu.trace_backend.entity.po.Role updateRole = edu.hrbu.trace_backend.entity.po.Role.builder()
                .rid(role.getRid())
                .name(role.getName())
                .memo(role.getMemo()).build();
        edu.hrbu.trace_backend.entity.po.Role exist = roleMapper.selectById(role.getRid());
        if (exist.getName().equals(role.getName())) {
            int updateRoleStatue = roleMapper.updateById(updateRole);
            roleMenueContrastMapper.delete(deleterContrastWrapper);
            midSet.forEach(mid -> {
                RoleMenueContrast insertContrast = RoleMenueContrast.builder()
                        .rid(role.getRid())
                        .mid(mid).build();
                roleMenueContrastMapper.insert(insertContrast);
            });
            return updateRoleStatue > 0 ?
                    Result
                            .ok(Message.EDIT_ROLE_SUCCESS.getValue())
                            .data("createAid", role.getRid())
                    :
                    Result
                            .fail(Message.EDIT_ROLE_FAIL.getValue())
                            .data("createAid", role.getRid());
        } else {
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Role> existWrapper = new QueryWrapper<>();
            existWrapper.eq("name", role.getName());
            if (!roleMapper.selectList(existWrapper).isEmpty()) {
                return Result
                        .fail(Message.ROLE_EXIST.getValue())
                        .data("createAid", role.getRid());
            }
            int updateRoleStatue = roleMapper.updateById(updateRole);
            roleMenueContrastMapper.delete(deleterContrastWrapper);
            midSet.forEach(mid -> {
                RoleMenueContrast insertContrast = RoleMenueContrast.builder()
                        .rid(role.getRid())
                        .mid(mid).build();
                roleMenueContrastMapper.insert(insertContrast);
            });
            return updateRoleStatue > 0 ?
                    Result
                            .ok(Message.EDIT_ROLE_SUCCESS.getValue())
                            .data("createAid", role.getRid())
                    :
                    Result
                            .fail(Message.EDIT_ROLE_FAIL.getValue())
                            .data("createAid", role.getRid());
        }
    }

    @Override
    public Result requestRoleAdd(Role role) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        List<Integer> menues = role.getMenues();
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Role> existWrapper = new QueryWrapper<>();
        existWrapper.eq("name", role.getName());
        if (!roleMapper.selectList(existWrapper).isEmpty()) {
            return Result
                    .fail(Message.ADD_ROLE_EXIST.getValue())
                    .data("createAid", currentAccountId);
        }
        edu.hrbu.trace_backend.entity.po.Role addRole = edu.hrbu.trace_backend.entity.po.Role.builder()
                .name(role.getName())
                .memo(Objects.equals(role.getMemo(), "") || role.getMemo() == null ? "" : role.getMemo()).build();
        int insertStatue = roleMapper.insert(addRole);
        if (insertStatue <= 0) {
            return Result
                    .fail(Message.ADD_ROLE_FAIL.getValue())
                    .data("createAid", currentAccountId);
        }
        Set<Integer> midSet = new HashSet<>(menues);
        List<MenueReverse> reverseList = menueMapper.selectMenueIdReverseBySon(menues);
        reverseList.forEach(reverse -> {
            midSet.add(reverse.getParent());
            midSet.add(reverse.getFather());
        });
        midSet.forEach(mid -> {
            RoleMenueContrast insertContrast = RoleMenueContrast.builder()
                    .rid(addRole.getRid())
                    .mid(mid).build();
            roleMenueContrastMapper.insert(insertContrast);
        });
        return Result
                .ok(Message.ADD_ROLE_SUCCESS.getValue())
                .data("createAid", addRole.getRid());
    }

    @Override
    public Result requestEnableAllRole(Able able) {
        if (!able.getVerify().equalsIgnoreCase(able.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - able.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Role> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("rid", 1)
                .and(ban -> ban.ne("ban", 0))
                .and(del -> del.ne("del", 1));
        edu.hrbu.trace_backend.entity.po.Role updateRole = edu.hrbu.trace_backend.entity.po.Role.builder()
                .ban(0).build();
        roleMapper.update(updateRole, queryWrapper);
        return Result.ok(Message.ENABLE_ALL_ROLE.getValue());
    }

    @Override
    public Result requestDisableAllRole(Able able) {
        if (!able.getVerify().equalsIgnoreCase(able.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - able.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.ne("rid", 1);
        List<edu.hrbu.trace_backend.entity.po.Account> accounts = accountMapper.selectList(accountQueryWrapper);
        if (!accounts.isEmpty()) {
            accounts.forEach(account -> {
                edu.hrbu.trace_backend.entity.po.Account updateAccount = edu.hrbu.trace_backend.entity.po.Account.builder()
                        .aid(account.getAid())
                        .ban(1).build();
                accountMapper.updateById(updateAccount);
            });
        }
        QueryWrapper<edu.hrbu.trace_backend.entity.po.Role> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .ne("rid", 1)
                .and(ban -> ban.ne("ban", 1));
        edu.hrbu.trace_backend.entity.po.Role updateRole = edu.hrbu.trace_backend.entity.po.Role.builder()
                .ban(1).build();
        roleMapper.update(updateRole, queryWrapper);
        return Result.ok(Message.DISABLE_ALL_ROLE.getValue());
    }

    @Override
    public Result requestSensitiveAccountInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        IPage<AccountOperate> page = new Page<>(currentPage, pageSize);
        IPage<AccountOperate> accountOperateIPage = accountOperateMapper.selectAccountOperateByCondition(page, keyword);
        if (!accountOperateIPage.getRecords().isEmpty()) {
            accountOperateIPage.getRecords().forEach(record -> {
                AccountInfo operate = record.getOperator();
                AccountInfo account = record.getAccount();
                operate.setAvatar(avatarPath + operate.getAvatar());
                account.setAvatar(avatarPath + account.getAvatar());
                record.setOperator(operate);
                record.setAccount(account);
            });
        }
        return Result
                .ok(Message.GET_ACCOUNT_OPERATE_SUCCESS.getValue())
                .data("iPage", accountOperateIPage);
    }

    @Override
    public Result requestSensitiveEnterpriseInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        IPage<EnterpriseOperate> page = new Page<>(currentPage, pageSize);
        IPage<EnterpriseOperate> enterpriseOperateIpage = enterpriseOperateMapper.selectEnterpriseOperateByCondition(page, keyword);
        if (!enterpriseOperateIpage.getRecords().isEmpty()) {
            enterpriseOperateIpage.getRecords().forEach(record -> {
                AccountInfo operate = record.getOperator();
                operate.setAvatar(avatarPath + operate.getAvatar());
            });
        }
        return Result
                .ok(Message.GET_ENTERPRISE_OPERATE_SUCCESS.getValue())
                .data("iPage", enterpriseOperateIpage);
    }

    @Override
    public Result requestSensitiveRoleInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        IPage<RoleOperate> page = new Page<>(currentPage, pageSize);
        IPage<RoleOperate> roleOperatePage = roleOperateMapper.selectRoleOperateByCondition(page, keyword);
        if (!roleOperatePage.getRecords().isEmpty()) {
            roleOperatePage.getRecords().forEach(record -> {
                AccountInfo operate = record.getOperator();
                operate.setAvatar(avatarPath + operate.getAvatar());
            });
        }
        return Result
                .ok(Message.GET_ROLE_OPERATE_SUCCESS.getValue())
                .data("iPage", roleOperatePage);
    }

    private static Map<String, Object> getAccountInfoSearchCondition(UserQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("gander", query.getGander());
        condition.put("tel", query.getTel());
        condition.put("role", query.getRole());
        condition.put("email", query.getEmail());
        condition.put("address", query.getAddress());
        condition.put("zipCode", query.getZipCode());
        condition.put("del", query.getDel());
        condition.put("ban", query.getBan());
        return condition;
    }

    private static Map<String, Object> getEnterpriseInfoSearchCondition(EnterpriseQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("legalPerson", query.getLegalPerson());
        condition.put("tel", query.getTel());
        condition.put("socialCode", query.getSocialCode());
        condition.put("address", query.getAddress());
        condition.put("zipCode", query.getZipCode());
        condition.put("del", query.getDel());
        return condition;
    }

    private static Map<String, Object> getRoleInfoSearchCondition(RoleQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("del", query.getDel());
        condition.put("ban", query.getBan());
        return condition;
    }
}
