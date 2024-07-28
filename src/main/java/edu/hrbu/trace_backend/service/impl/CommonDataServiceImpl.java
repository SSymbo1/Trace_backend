package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Decode;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.Account;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.mapper.AccountInfoMapper;
import edu.hrbu.trace_backend.mapper.AccountMapper;
import edu.hrbu.trace_backend.mapper.AccountOperateMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.service.CommonDataService;
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
public class CommonDataServiceImpl implements CommonDataService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Value("${resources.avatar}")
    private String avatarPath;

    @Override
    public Result requestHomeStatisticsCardData() {
        return null;
    }

    @Override
    public Result requestHomeStatisticsLineData() {
        return null;
    }

    @Override
    public Result requestWhoIs() {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        AccountInfo loginAccountInfo = accountInfoMapper.selectAccountInfoByAccountId(currentAccountId);
        loginAccountInfo.setAvatar(avatarPath + loginAccountInfo.getAvatar());
        return Result
                .ok(Message.GET_LOGIN_ACCOUNT_INFO_SUCCESS.getValue())
                .data("login", loginAccountInfo);
    }

    @Override
    public Result requestDecodePass(Decode decode) {
        if (!decode.getVerify().equalsIgnoreCase(decode.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - decode.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        if (accountMapper.selectById(currentAccountId).getRid() != 1) {
            return Result.fail(Message.NO_PERMISSION.getValue());
        }
        return Result
                .ok(Message.DECODED.getValue())
                .data("decode", AesUtil.decryptStr(decode.getEncodePass()));
    }

    @Override
    public Result requestEditAccountInfo(Integer accountId) {
        Map<String, Object> data = new HashMap<>();
        Account account = accountMapper.selectById(accountId);
        AccountInfo accountInfo = accountInfoMapper.selectById(accountId);
        data.put("username", account.getUsername());
        data.put("password", AesUtil.decryptStr(account.getPassword()));
        data.put("role",account.getRid());
        data.put("enterprise",accountInfo.getEid());
        data.put("name",accountInfo.getName());
        data.put("gander",accountInfo.getGander());
        data.put("tel",accountInfo.getTel());
        data.put("email",accountInfo.getEmail());
        data.put("address",accountInfo.getAddress());
        data.put("zipCode",accountInfo.getZipCode());
        data.put("avatar",avatarPath+accountInfo.getAvatar());
        return Result
                .ok(Message.GET_ACCOUNT_EDIT_DATA.getValue())
                .data("form", data);
    }

    @Override
    public Result requestEditEnterpriseInfo(Integer enterpriseId) {
        return Result
                .ok(Message.GET_ENTERPRISE_EDIT_DATA.getValue())
                .data("form",enterpriseMapper.selectById(enterpriseId));
    }
}
