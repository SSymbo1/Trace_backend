package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.UserQuery;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import edu.hrbu.trace_backend.mapper.AccountInfoMapper;
import edu.hrbu.trace_backend.service.SystemService;
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
    @Value("${resources.avatar}")
    private String avatarPath;

    @Override
    public Result requestAccountInfoPaged(UserQuery query) {
        IPage<AccountInfo> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = new HashMap<>();
        condition.put("name",query.getName());
        condition.put("gander",query.getGander());
        condition.put("tel",query.getTel());
        condition.put("role",query.getRole());
        condition.put("email",query.getEmail());
        condition.put("address",query.getAddress());
        condition.put("zipCode",query.getZipCode());
        condition.put("del", query.isDel() ?1:0);
        condition.put("ban",query.isBan() ?1:0);
        IPage<AccountInfo> accountInfoIPage = accountInfoMapper.selectAccountInfoByCondition(page, condition);
        accountInfoIPage.getRecords().forEach(record-> record.setAvatar(avatarPath + record.getAvatar()));
        return Result
                .ok(Message.GET_ACCOUNT_INFO_SUCCESS.getValue())
                .data("iPage",accountInfoIPage);
    }
}
