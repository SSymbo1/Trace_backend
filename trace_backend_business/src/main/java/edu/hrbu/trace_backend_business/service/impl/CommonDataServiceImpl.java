package edu.hrbu.trace_backend_business.service.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.OnlineContext;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.Decode;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.po.*;
import edu.hrbu.trace_backend_business.mapper.*;
import edu.hrbu.trace_backend_business.service.CommonDataService;
import edu.hrbu.trace_backend_business.util.AesUtil;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CommonDataServiceImpl implements CommonDataService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private MenueMapper menueMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductRecordMapper productRecordMapper;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Value("${resources.avatar}")
    private String avatarPath;

    @Override
    public Result requestHomeStatisticsCardData() {
        Map<String, Object> data = new HashMap<>();
        int accountCount = accountMapper.selectAccountCount();
        int enterpriseCount = enterpriseMapper.selectEnterpriseCount();
        int majorCount = productRecordMapper.selectProductRecordCount();
        int traceCount = statisticsMapper.selectTraceDataCount();
        data.put("account", accountCount);
        data.put("enterprise", enterpriseCount);
        data.put("major", majorCount);
        data.put("trace", traceCount);
        return Result
                .ok(Message.HOME_CARD_DATA_SUCCESS.getValue())
                .data("data", data);
    }

    @Override
    public Result requestHomeStatisticsLineData() {
        DateTime currentTime = new DateTime(DateTime.now());
        DateTime beforeTime = currentTime.offset(DateField.DAY_OF_MONTH, -15);
        currentTime = new DateTime(DateTime.now());
        List<DateTime> range = DateUtil.rangeToList(
                beforeTime,
                currentTime,
                DateField.DAY_OF_YEAR
        );
        List<String> timeRange = new ArrayList<>();
        range.forEach(time -> timeRange.add(time.toString(Format.FULL_DATE_FORMAT.getValue())));
        Map<String, Object> data = new HashMap<>();
        List<String> timeData = new ArrayList<>();
        List<Integer> histogramData = new ArrayList<>();
        timeRange.forEach(time -> {
            timeData.add(time);
            histogramData.add(statisticsMapper.selectMonitorHistogramDataByTimeBetween(time));
        });
        data.put("time", timeData);
        data.put("data", histogramData);
        return Result
                .ok(Message.GET_HOME_HISTOGRAM_SUCCESS.getValue())
                .data("data", data);
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
        data.put("role", account.getRid());
        data.put("enterprise", accountInfo.getEid());
        data.put("name", accountInfo.getName());
        data.put("gander", accountInfo.getGander());
        data.put("tel", accountInfo.getTel());
        data.put("email", accountInfo.getEmail());
        data.put("address", accountInfo.getAddress());
        data.put("zipCode", accountInfo.getZipCode());
        data.put("avatar", avatarPath + accountInfo.getAvatar());
        return Result
                .ok(Message.GET_ACCOUNT_EDIT_DATA.getValue())
                .data("form", data);
    }

    @Override
    public Result requestEditEnterpriseInfo(Integer enterpriseId) {
        Enterprise data = enterpriseMapper.selectById(enterpriseId);
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        supplierQueryWrapper.eq("eid", enterpriseId);
        Supplier supplier = supplierMapper.selectOne(supplierQueryWrapper);
        data.setType(supplier.getType());
        return Result
                .ok(Message.GET_ENTERPRISE_EDIT_DATA.getValue())
                .data("form", data);
    }

    @Override
    public Result requestEditRoleInfo(Integer roleId) {
        Map<String, Object> data = new HashMap<>();
        Role role = roleMapper.selectById(roleId);
        QueryWrapper<RoleMenueContrast> roleMenueContrastQueryWrapper = new QueryWrapper<>();
        roleMenueContrastQueryWrapper.eq("rid", roleId);
        List<Integer> menuIds = menueMapper.selectChildMenueIdByRoleId(roleId);
        data.put("name", role.getName());
        data.put("memo", role.getMemo());
        data.put("menues", menuIds);
        return Result
                .ok(Message.GET_ROLE_EDIT_DATA.getValue())
                .data("form", data);
    }

    @Override
    public Result requestProductInfo(Integer productId) {
        Map<String, Object> data = new HashMap<>();
        Product product = productMapper.selectOne(new QueryWrapper<Product>().eq("pid", productId));
        ProductRecord productRecord = productRecordMapper.selectOne(
                new QueryWrapper<ProductRecord>().eq("pid", productId)
        );
        data.put("name", product.getName());
        data.put("code", product.getCode());
        data.put("enterprise", product.getEid());
        data.put("num", productRecord.getNum());
        data.put("unit", product.getUnit());
        data.put("isMajor", product.getIsMajor());
        data.put("cid", product.getCid());
        return Result
                .ok(Message.GET_PRODUCT_EDIT_DATA.getValue())
                .data("form", data);
    }

    @Override
    public Result requestApproveInfo(Integer approverId) {
        return Result
                .ok(Message.GET_ACCOUNT_INFO_SUCCESS.getValue())
                .data("data", accountInfoMapper.selectById(approverId));
    }
}
