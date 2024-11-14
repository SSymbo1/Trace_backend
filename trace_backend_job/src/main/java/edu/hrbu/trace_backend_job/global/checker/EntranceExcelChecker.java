package edu.hrbu.trace_backend_job.global.checker;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import edu.hrbu.trace_backend_job.entity.enums.Exception;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_job.entity.excel.Entrance;
import edu.hrbu.trace_backend_job.entity.po.Classification;
import edu.hrbu.trace_backend_job.entity.po.Enterprise;
import edu.hrbu.trace_backend_job.global.exception.excel.ExcelNullException;
import edu.hrbu.trace_backend_job.global.exception.excel.ExcelStructException;
import edu.hrbu.trace_backend_job.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend_job.mapper.ClassificationMapper;
import edu.hrbu.trace_backend_job.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_job.mapper.ProductRecordMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class EntranceExcelChecker extends AnalysisEventListener<Entrance> {

    private final ProductRecordMapper productRecordMapper = SpringUtil.getBean(ProductRecordMapper.class);
    private final ClassificationMapper classificationMapper = SpringUtil.getBean(ClassificationMapper.class);
    private final EnterpriseMapper enterpriseMapper = SpringUtil.getBean(EnterpriseMapper.class);

    private final List<String> TABLE_STRUCT = new ArrayList<>(Arrays.asList(
            "编号", "经营商户代码", "经营商户名称", "产品代码", "产品名称", "批次号", "追溯码", "数量", "单位", "产品分类", "买方类型"
    ));
    private int dataLine = 0;

    @Override
    public void invoke(Entrance entrance, AnalysisContext analysisContext) {
        if (entrance == null || Stream.of(
                entrance.getId(),
                entrance.getBusinessCode(),
                entrance.getBusinessName(),
                entrance.getCode(),
                entrance.getName(),
                entrance.getBatch(),
                entrance.getNum(),
                entrance.getUnit(),
                entrance.getClassName(),
                entrance.getBuyerType()
        ).anyMatch(Objects::isNull)) {
            log.error("第{}行数据存在空值", dataLine);
            throw new ExcelNullException(Exception.EXCEL_LINE_NULL.getValue());
        }
        if (enterpriseMapper.selectList(
                new QueryWrapper<Enterprise>()
                        .eq("name", entrance.getBusinessName())
                        .and(eid -> eid.eq("social_code", entrance.getBusinessCode()))
        ).isEmpty()) {
            log.error("企业未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_ENTERPRISE_REGISTERED.getValue());
        }
        if (classificationMapper.selectList(
                new QueryWrapper<Classification>().eq("name", entrance.getClassName())
        ).isEmpty()) {
            log.error("产品分类未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_CLASS.getValue());
        }
        if (productRecordMapper.selectProductRecordByCondition(entrance.getName(), entrance.getCode()) == 0) {
            log.error("商品未在系统备案，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_PRODUCT_NO_PROCESS.getValue());
        }
        if (!entrance.getBuyerType().equals("个人") && !entrance.getBuyerType().equals("组织")) {
            log.info("买方类型错误!");
            throw new ExcelTypeException(Exception.EXCEL_BUYER_TYPE_ERROR.getValue());
        }
        if (
                (entrance.getTraceCode() == null || entrance.getTraceCode().isEmpty()) &&
                        (productRecordMapper.selectExistProductRecordByCondition(
                                entrance.getBusinessCode(), entrance.getCode(), entrance.getName()) == 0)
        ) {
            log.error("非产品生产商且未填写追溯码，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_TRACE_ERROR.getValue());
        }
        dataLine++;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (headMap.size() != TABLE_STRUCT.size()) {
            log.error("超市出场备案表头数量不匹配，期望 {} 个，实际 {} 个", TABLE_STRUCT.size(), headMap.size());
            throw new ExcelStructException(Exception.EXCEL_STRUCT_HEAD_NUM.getValue());
        }
        if (!headMap.values().containsAll(TABLE_STRUCT)) {
            log.error("超市出场备案表头不匹配，缺失的表头有: {}", TABLE_STRUCT.stream()
                    .filter(header -> !headMap.containsValue(header))
                    .collect(Collectors.toSet()));
            throw new ExcelStructException(Exception.EXCEL_STRUCT_HEAD.getValue() + TABLE_STRUCT.stream()
                    .filter(header -> !headMap.containsValue(header))
                    .collect(Collectors.joining(",")));
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (dataLine == 0) {
            log.error("超市出场备案表无数据，无法导入!");
            throw new ExcelNullException(Exception.EXCEL_NULL.getValue());
        }
    }
}
