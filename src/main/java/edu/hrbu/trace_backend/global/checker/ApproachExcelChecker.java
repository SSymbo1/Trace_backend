package edu.hrbu.trace_backend.global.checker;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.enums.Exception;
import edu.hrbu.trace_backend.entity.excel.Approach;
import edu.hrbu.trace_backend.entity.po.Classification;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.global.exception.excel.ExcelNullException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelStructException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend.mapper.ClassificationMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.EntranceMapper;
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
    private final EntranceMapper entranceMapper = SpringUtil.getBean(EntranceMapper.class);

    private final List<String> TABLE_STRUCT = new ArrayList<>(Arrays.asList(
            "编号", "经营商户代码", "经营商户名称", "产品代码", "产品名称", "批次号", "追溯码", "数量", "单位", "产品分类"
    ));
    private int dataLine = 0;

    @Override
    public void invoke(Approach approach, AnalysisContext analysisContext) {
        if (approach == null || Stream.of(
                approach.getId(),
                approach.getBusinessCode(),
                approach.getBusinessName(),
                approach.getCode(),
                approach.getTraceCode(),
                approach.getName(),
                approach.getBatch(),
                approach.getNum(),
                approach.getUnit(),
                approach.getClassName()
        ).anyMatch(Objects::isNull)) {
            log.error("第{}行数据存在空值", dataLine);
            throw new ExcelNullException(Exception.EXCEL_LINE_NULL.getValue());
        }
        if (enterpriseMapper.selectList(
                new QueryWrapper<Enterprise>()
                        .eq("name", approach.getBusinessName())
                        .and(eid -> eid.eq("social_code", approach.getBusinessCode()))
        ).isEmpty()) {
            log.error("企业未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_ENTERPRISE_REGISTERED.getValue());
        }
        if (classificationMapper.selectList(
                new QueryWrapper<Classification>()
                        .eq("name", approach.getClassName())
        ).isEmpty()) {
            log.error("产品分类未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_CLASS.getValue());
        }
        if (productRecordMapper.selectProductRecordByCondition(approach.getName(), approach.getCode()) == 0) {
            log.error("商品未在系统备案，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_PRODUCT_NO_PROCESS.getValue());
        }
        if (entranceMapper.selectTraceDataExistByCondition(
                approach.getName(),
                approach.getCode(),
                approach.getTraceCode()) == 0) {
            log.error("存在未在出场记录中进行追溯的数据:{}，无法导入", approach.getName());
            throw new ExcelTypeException(Exception.EXCEL_NO_TRACE.getValue() + approach.getName());
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
