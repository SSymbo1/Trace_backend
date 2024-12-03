package edu.hrbu.trace_backend_business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.OnlineContext;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.Decode;
import edu.hrbu.trace_backend_business.entity.dto.PlatformMap;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.enums.RedisKey;
import edu.hrbu.trace_backend_business.entity.po.*;
import edu.hrbu.trace_backend_business.entity.po.json.AreaData;
import edu.hrbu.trace_backend_business.mapper.*;
import edu.hrbu.trace_backend_business.service.CommonDataService;
import edu.hrbu.trace_backend_business.util.AesUtil;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
    private EntranceMapper entranceMapper;
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private ClassificationMapper classificationMapper;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private PlatformDataMapper platformDataMapper;
    @Value("${resources.avatar}")
    private String avatarPath;

    @Override
    public Result requestHomeStatisticsCardData() {
        int loginUser = 0;
        Map<String, Object> data = new HashMap<>();
        int accountCount = accountMapper.selectAccountCount();
        int enterpriseCount = enterpriseMapper.selectEnterpriseCount();
        int majorCount = productRecordMapper.selectProductRecordCount();
        int traceCount = statisticsMapper.selectTraceDataCount();
        Set<String> loginUserSet = stringRedisTemplate.keys(RedisKey.USER.getValue() + "*");
        if (loginUserSet != null) {
            loginUser = loginUserSet.size();
        }
        data.put("loginUser", loginUser);
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
        List<String> timeRange = edu.hrbu.trace_backend_business.util.DateUtil.getRangeBeforeNowDateList(-15);
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
    public Result requestPlatformDataCollectData() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> fromData = new HashMap<>();
        Map<String, Object> classData = new HashMap<>();
        classData.put("1", 0);
        classData.put("2", 0);
        classData.put("3", 0);
        classData.put("4", 0);
        List<Entrance> entrances = entranceMapper.selectList(null);
        List<Approach> approaches = approachMapper.selectList(null);
        List<Product> products = productMapper.selectList(null);
        List<Integer> productRecord = productRecordMapper.selectProductRecordClassID();
        for (Entrance entrance : entrances) {
            Integer updateKey = updateClassCollectMap(entrance.getCid());
            classData.put(updateKey.toString(), (Integer) classData.get(updateKey.toString()) + 1);
        }
        for (Approach approach : approaches) {
            Integer updateKey = updateClassCollectMap(approach.getCid());
            classData.put(updateKey.toString(), (Integer) classData.get(updateKey.toString()) + 1);
        }
        for (Product product : products) {
            Integer updateKey = updateClassCollectMap(product.getCid());
            classData.put(updateKey.toString(), (Integer) classData.get(updateKey.toString()) + 1);
        }
        for (Integer cid : productRecord) {
            Integer updateKey = updateClassCollectMap(cid);
            classData.put(updateKey.toString(), (Integer) classData.get(updateKey.toString()) + 1);
        }
        Map<String, Object> classNameData = new HashMap<>();
        classData.forEach((k, v) -> {
            Classification classification = classificationMapper.selectOne(
                    new QueryWrapper<Classification>().eq("cid", Integer.valueOf(k))
            );
            classNameData.put(classification.getName(), v);
        });
        Map<String, Object> product = productMapper.selectDataCollectFromCount("product");
        Map<String, Object> record = productMapper.selectDataCollectFromCount("record");
        Map<String, Object> approach = productMapper.selectDataCollectFromCount("approach");
        Map<String, Object> entrance = productMapper.selectDataCollectFromCount("entrance");
        fromData.put("product", product.get("产品备案"));
        fromData.put("record", record.get("备案审核"));
        fromData.put("approach", approach.get("超市进场"));
        fromData.put("entrance", entrance.get("超市出场"));
        data.put("from", fromData);
        data.put("class", classNameData);
        return Result
                .ok(Message.PLATFORM.getValue())
                .data("platform", data);
    }

    @Override
    public Result requestPlatformEnterpriseData() {
        Map<String, Integer> enterpriseTypeCount = new HashMap<>();
        List<String> timeRange = edu.hrbu.trace_backend_business.util.DateUtil.getRangeBeforeNowDateList(-15);
        timeRange.remove(timeRange.size() - 1);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> enterpriseBarData = new HashMap<>();
        List<Integer> enterpriseCount = new ArrayList<>();
        for (EnterpriseType initEnum : EnterpriseType.values()) {
            enterpriseTypeCount.put(initEnum.getKey(), 0);
        }
        for (String time : timeRange) {
            PlatformData platformData = platformDataMapper.selectOne(
                    new QueryWrapper<PlatformData>()
                            .eq("name", "enterprise")
                            .and(date -> date.eq("date", time))
            );
            enterpriseCount.add(platformData == null ? 0 : platformData.getCount());
        }
        List<Enterprise> enterprises = enterpriseMapper.selectList(
                new QueryWrapper<Enterprise>()
                        .ne("del", 1)
                        .and(eid -> eid.ne("eid", 1))
        );
        if (!enterprises.isEmpty()) {
            for (Enterprise enterprise : enterprises) {
                enterpriseTypeCount.put(
                        EnterpriseType.getEnterpriseTypeByValue(enterprise.getIlk())
                                .get()
                                .getKey(),
                        enterpriseTypeCount.get(EnterpriseType.getEnterpriseTypeByValue(enterprise.getIlk())
                                .get()
                                .getKey()) + 1
                );
            }
        }
        enterpriseBarData.put("time", timeRange);
        enterpriseBarData.put("value", enterpriseCount);
        data.put("enterprise", enterpriseBarData);
        data.put("enterpriseType", enterpriseTypeCount);
        return Result
                .ok(Message.PLATFORM.getValue())
                .data("platform", data);
    }

    @Override
    public Result requestPlatformProductData() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> productData = new HashMap<>();
        List<Integer> processProduct = new ArrayList<>();
        List<Integer> insertProduct = new ArrayList<>();
        Map<String, Object> classData = new HashMap<>();
        classData.put("1", 0);
        classData.put("2", 0);
        classData.put("3", 0);
        classData.put("4", 0);
        List<String> timeRange = edu.hrbu.trace_backend_business.util.DateUtil.getRangeBeforeNowDateList(-15);
        for (String time : timeRange) {
            Integer insert = productRecordMapper.selectProductRecordByDataCondition(time, "insert");
            Integer process = productRecordMapper.selectProductRecordByDataCondition(time, "process");
            processProduct.add(process);
            insertProduct.add(insert);
        }
        List<Integer> classIds = productRecordMapper.selectProductRecordClassIdByTime(
                timeRange.get(0), timeRange.get(timeRange.size() - 1)
        );
        for (Integer cid : classIds) {
            Integer classId = updateClassCollectMap(cid);
            classData.put(String.valueOf(classId), (Integer) classData.get(String.valueOf(classId)) + 1);
        }
        Map<String, Object> classNameData = new HashMap<>();
        classData.forEach((k, v) -> {
            Classification classification = classificationMapper.selectOne(
                    new QueryWrapper<Classification>().eq("cid", Integer.valueOf(k))
            );
            classNameData.put(classification.getName(), v);
        });
        productData.put("time", timeRange);
        productData.put("process", processProduct);
        productData.put("insert", insertProduct);
        data.put("product", productData);
        data.put("class", classNameData);
        return Result
                .ok(Message.PLATFORM.getValue())
                .data("platform", data);
    }

    @Override
    public Result requestPlatformTraceData() {
        Map<String, Object> approachClassData = new HashMap<>();
        approachClassData.put("1", 0);
        approachClassData.put("2", 0);
        approachClassData.put("3", 0);
        approachClassData.put("4", 0);
        Map<String, Object> entranceClassData = new HashMap<>();
        entranceClassData.put("1", 0);
        entranceClassData.put("2", 0);
        entranceClassData.put("3", 0);
        entranceClassData.put("4", 0);
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> traceData = new HashMap<>();
        List<Integer> entranceCount = new ArrayList<>();
        List<Integer> approachCount = new ArrayList<>();
        List<String> timeRange = edu.hrbu.trace_backend_business.util.DateUtil.getRangeBeforeNowDateList(-15);
        for (String time : timeRange) {
            Integer approach = approachMapper.selectApproachDataCountByDate(time);
            Integer entrance = entranceMapper.selectEntranceDataCountByDate(time);
            entranceCount.add(entrance);
            approachCount.add(approach);
        }
        List<Entrance> entrances = entranceMapper.selectList(null);
        List<Approach> approaches = approachMapper.selectList(null);
        for (Entrance entrance : entrances) {
            Integer cid = updateClassCollectMap(entrance.getCid());
            entranceClassData.put(String.valueOf(cid), (Integer) entranceClassData.get(String.valueOf(cid)) + 1);
        }
        for (Approach approach : approaches) {
            Integer cid = updateClassCollectMap(approach.getCid());
            approachClassData.put(String.valueOf(cid), (Integer) approachClassData.get(String.valueOf(cid)) + 1);
        }
        Map<String, Object> approachClassNameData = new HashMap<>();
        Map<String, Object> entranceClassNameData = new HashMap<>();
        approachClassData.forEach((k, v) -> {
            Classification classification = classificationMapper.selectOne(
                    new QueryWrapper<Classification>().eq("cid", Integer.valueOf(k))
            );
            approachClassNameData.put(classification.getName(), v);
        });
        entranceClassData.forEach((k, v) -> {
            Classification classification = classificationMapper.selectOne(
                    new QueryWrapper<Classification>().eq("cid", Integer.valueOf(k))
            );
            entranceClassNameData.put(classification.getName(), v);
        });
        traceData.put("time", timeRange);
        traceData.put("entrance", entranceCount);
        traceData.put("approach", approachCount);
        data.put("traceData", traceData);
        data.put("approachClassData", approachClassNameData);
        data.put("entranceClassData", entranceClassNameData);
        return Result
                .ok(Message.PLATFORM.getValue())
                .data("platform", data);
    }

    @Override
    public Result requestPlatformMap() {
        Map<String, Object> data = new HashMap<>();
        Map<String, Integer> areaCount = new HashMap<>();
        for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
            areaCount.put(province.getKey(), province.getValue());
        }
        List<String> locationList = new ArrayList<>();
        List<PlatformMap> areaData = new ArrayList<>();
        List<String> productAddress = productMapper.selectProductEnterpriseAddress();
        List<String> processAddress = productRecordMapper.selectProductRecordEnterpriseAddress();
        List<String> entranceAddress = entranceMapper.selectEntranceAddress();
        List<String> approachAddress = approachMapper.selectApproachAddress();
        List<String> address = new ArrayList<>();
        address.addAll(productAddress);
        address.addAll(processAddress);
        address.addAll(entranceAddress);
        address.addAll(approachAddress);
        for (String addressStr : address) {
            for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
                if (addressStr.contains(province.getKey())) {
                    areaCount.put(province.getKey(), areaCount.get(province.getKey()) + 1);
                    break;
                }
            }
        }
        areaCount.forEach((area, count) -> {
            PlatformMap platformMap = PlatformMap.builder()
                    .name(area)
                    .value(count).build();
            areaData.add(platformMap);
        });
        data.put("area", areaData);
        return Result
                .ok(Message.PLATFORM.getValue())
                .data("platform", data);
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

    private Integer updateClassCollectMap(Integer cid) {
        Classification classification = classificationMapper.selectOne(
                new QueryWrapper<Classification>().eq("cid", cid)
        );
        if (classification.getParent() != 0) {
            return classification.getParent();
        } else {
            return classification.getCid();
        }
    }

}
