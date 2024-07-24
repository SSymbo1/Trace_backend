package edu.hrbu.trace_backend.service.impl;

import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import edu.hrbu.trace_backend.mapper.AccountInfoMapper;
import edu.hrbu.trace_backend.service.CommonDataService;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CommonDataServiceImpl implements CommonDataService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
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
        Integer currentAccountId= Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        AccountInfo loginAccountInfo = accountInfoMapper.selectAccountInfoByAccountId(currentAccountId);
        loginAccountInfo.setAvatar(avatarPath+loginAccountInfo.getAvatar());
        return Result
                .ok(Message.GET_LOGIN_ACCOUNT_INFO_SUCCESS.getValue())
                .data("login",loginAccountInfo);
    }
}
