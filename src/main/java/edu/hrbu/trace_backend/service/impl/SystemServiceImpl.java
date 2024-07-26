package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Account;
import edu.hrbu.trace_backend.entity.dto.AccountStatue;
import edu.hrbu.trace_backend.entity.dto.UserQuery;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import edu.hrbu.trace_backend.entity.po.AccountOperate;
import edu.hrbu.trace_backend.mapper.AccountInfoMapper;
import edu.hrbu.trace_backend.mapper.AccountMapper;
import edu.hrbu.trace_backend.mapper.AccountOperateMapper;
import edu.hrbu.trace_backend.service.SystemService;
import edu.hrbu.trace_backend.util.AesUtil;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SystemServiceImpl implements SystemService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountOperateMapper accountOperateMapper;
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
        int updateStatue = accountMapper.updateById(updateAccountStatue);
        if (updateStatue <= 0) {
            return Result.fail(Message.SET_ACCOUNT_STATUE_FAIL.getValue());
        }
        return Result.ok(Message.SET_ACCOUNT_STATUE_SUCCESS.getValue());
    }

    @Override
    public Result requestAccountEdit(Account account) {
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
                            .fail(Message.EDIT_ACCOUNT_FAIL.getValue()).
                            data("createAid", account.getAid());
        } else {
            QueryWrapper<edu.hrbu.trace_backend.entity.po.Account> existWrapper = new QueryWrapper<>();
            existWrapper.eq("username", account.getUsername());
            if (!accountMapper.selectList(existWrapper).isEmpty()) {
                return Result
                        .fail(Message.EDIT_ACCOUNT_FAIL.getValue())
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
}
