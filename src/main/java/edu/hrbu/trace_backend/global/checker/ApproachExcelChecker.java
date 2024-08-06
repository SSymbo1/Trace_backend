package edu.hrbu.trace_backend.global.checker;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.enums.Exception;
import edu.hrbu.trace_backend.entity.excel.Approach;
import edu.hrbu.trace_backend.entity.po.Classification;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.entity.po.ProductRecord;
import edu.hrbu.trace_backend.global.exception.excel.ExcelNullException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelStructException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend.mapper.ClassificationMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.ProductRecordMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ApproachExcelChecker extends AnalysisEventListener<Approach> {

    private final ProductRecordMapper productRecordMapper = SpringUtil.getBean(ProductRecordMapper.class);
    private final ClassificationMapper classificationMapper = SpringUtil.getBean(ClassificationMapper.class);
    private final EnterpriseMapper enterpriseMapper = SpringUtil.getBean(EnterpriseMapper.class);

    private final List<String> TABLE_STRUCT = new ArrayList<>(Arrays.asList(
            "编号", "经营商户代码", "经营商户名称", "产品代码", "产品名称", "批次号", "数量", "单位", "产品分类", "供应商名称"
    ));
    private int dataLine = 0;

    @Override
    public void invoke(Approach approach, AnalysisContext analysisContext) {
        if (approach == null || Stream.of(
                approach.getId(),
                approach.getBusinessCode(),
                approach.getBusinessName(),
                approach.getCode(),
                approach.getName(),
                approach.getBatch(),
                approach.getNum(),
                approach.getUnit(),
                approach.getClassName()
        ).anyMatch(Objects::isNull)) {
            log.error("第{}行数据存在空值", dataLine);
            throw new ExcelNullException(Exception.EXCEL_LINE_NULL.getValue());
        }
        QueryWrapper<Classification> classificationQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Enterprise> enterpriseQueryWrapper = new QueryWrapper<>();
        classificationQueryWrapper.eq("name", approach.getClassName());
        QueryWrapper<Enterprise> supplierQueryWrapper = new QueryWrapper<>();
        enterpriseQueryWrapper
                .eq("name", approach.getBusinessName())
                .and(eid -> eid.eq("social_code", approach.getBusinessCode()));
        supplierQueryWrapper.eq("name", approach.getSupplier());
        if (enterpriseMapper.selectList(enterpriseQueryWrapper).isEmpty()) {
            log.error("企业未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_ENTERPRISE_REGISTERED.getValue());
        }
        if (classificationMapper.selectList(classificationQueryWrapper).isEmpty()) {
            log.error("产品分类未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_CLASS.getValue());
        }
        if (productRecordMapper.selectProductRecordByCondition(approach.getName(), approach.getCode()) == 0) {
            log.error("商品未在系统备案，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_PRODUCT_NO_PROCESS.getValue());
        }
        if (enterpriseMapper.selectList(supplierQueryWrapper).isEmpty()) {
            log.error("供销商未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_ENTERPRISE_REGISTERED.getValue());
        }
        dataLine++;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (headMap.size() != TABLE_STRUCT.size()) {
            log.error("超市进场备案表头数量不匹配，期望 {} 个，实际 {} 个", TABLE_STRUCT.size(), headMap.size());
            throw new ExcelStructException(Exception.EXCEL_STRUCT_HEAD_NUM.getValue());
        }
        if (!headMap.values().containsAll(TABLE_STRUCT)) {
            log.error("超市进场备案表头不匹配，缺失的表头有: {}", TABLE_STRUCT.stream()
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
            log.error("超市进场备案表无数据，无法导入!");
            throw new ExcelNullException(Exception.EXCEL_NULL.getValue());
        }
    }
}
