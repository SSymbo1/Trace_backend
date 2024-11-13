package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.applet.TraceProduct;
import edu.hrbu.trace_backend.entity.enums.Exception;
import edu.hrbu.trace_backend.entity.enums.Format;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.global.exception.CompareException;
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.service.AppletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class AppletServiceImpl implements AppletService {

    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductRecordMapper productRecordMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private ApproachMapper approachMapper;
    @Value("${resources.goods}")
    private String photoPath;

    @Override
    public Result requestTraceProductInfo(String traceCode) {
        List<Entrance> entrances = entranceMapper.selectList(
                new QueryWrapper<Entrance>()
                        .eq("trace", traceCode)
                        .orderByAsc("business_time")
        );
        List<Approach> approaches = approachMapper.selectList(
                new QueryWrapper<Approach>()
                        .eq("trace", traceCode)
                        .orderByAsc("business_time")
        );
        List<Map<String, Object>> traceData = new ArrayList<>();
        Product product = productMapper.selectOne(
                new QueryWrapper<Product>()
                        .eq("name", entrances.get(0).getName())
                        .and(code -> code.eq("code", entrances.get(0).getCode()))
        );
        product.setPhoto(photoPath + product.getPhoto());
        product.setEnterpriseName(enterpriseMapper.selectById(product.getEid()).getName());
        product.setProductRecord(productRecordMapper.selectOne(
                new QueryWrapper<ProductRecord>().eq("pid", product.getPid())
        ));
        entrances.forEach(entrance -> {
            Map<String, Object> data = new HashMap<>();
            data.put("time", entrance.getBusinessTime());
            data.put("enterprise", enterpriseMapper.selectOne(
                    new QueryWrapper<Enterprise>().eq("eid", entrance.getBid())
            ).getName());
            traceData.add(data);
        });
        approaches.forEach(approach -> {
            Map<String, Object> data = new HashMap<>();
            data.put("time", approach.getBusinessTime());
            data.put("enterprise", enterpriseMapper.selectOne(
                    new QueryWrapper<Enterprise>().eq("eid", approach.getEid())
            ).getName());
            traceData.add(data);
        });
        SimpleDateFormat sdf = new SimpleDateFormat(Format.FULL_TIME_FORMAT.getValue());
        traceData.sort((o1, o2) -> {
            Date oneData = null;
            Date anotherData = null;
            try {
                oneData = sdf.parse((String) o1.get("time"));
                anotherData = sdf.parse((String) o2.get("time"));
            } catch (ParseException e) {
                throw new CompareException(Exception.COMPARE_EXCEPTION.getValue());
            }
            return oneData.compareTo(anotherData);
        });
        TraceProduct traceProduct = TraceProduct.builder()
                .trace(traceCode)
                .batch(entrances.get(0).getBatch())
                .product(product)
                .traceData(traceData).build();
        return Result
                .ok(Message.GET_TRACE_DATA_APPLET_SUCCESS.getValue())
                .data("data", traceProduct);
    }

}
