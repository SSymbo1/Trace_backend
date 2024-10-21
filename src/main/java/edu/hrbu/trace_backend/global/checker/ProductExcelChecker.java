package edu.hrbu.trace_backend.global.checker;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.enums.Exception;
import edu.hrbu.trace_backend.entity.excel.ProductRecord;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import edu.hrbu.trace_backend.entity.po.Classification;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.global.exception.excel.ExcelNullException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelStructException;
import edu.hrbu.trace_backend.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend.mapper.AccountInfoMapper;
import edu.hrbu.trace_backend.mapper.ClassificationMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class ProductExcelChecker extends AnalysisEventListener<ProductRecord> {

    private final AccountInfoMapper accountInfoMapper = SpringUtil.getBean(AccountInfoMapper.class);
    private final ClassificationMapper classificationMapper = SpringUtil.getBean(ClassificationMapper.class);
    private final EnterpriseMapper enterpriseMapper = SpringUtil.getBean(EnterpriseMapper.class);

    private final List<String> TABLE_STRUCT = new ArrayList<>(Arrays.asList(
            "编号", "企业名称", "企业编码", "产品名称", "产品编码", "产品分类", "单位", "数量", "是否重要产品", "操作者"
    ));
    private int dataLine = 0;

    @Override
    public void invoke(ProductRecord productRecord, AnalysisContext analysisContext) {
        if (productRecord == null || Stream.of(
                productRecord.getId(),
                productRecord.getClassification(),
                productRecord.getCode(),
                productRecord.getEnterprise(),
                productRecord.getSocialCode(),
                productRecord.getName(),
                productRecord.getUnit(),
                productRecord.getNum(),
                productRecord.getIsMajor(),
                productRecord.getOperator()).anyMatch(Objects::isNull)) {
            log.error("第{}行数据存在空值", dataLine);
            throw new ExcelNullException(Exception.EXCEL_LINE_NULL.getValue());
        }
        if (productRecord.getName().length() > 25) {
            log.error("产品:{}名称过长，请控制在25个字符以内", productRecord.getName());
            throw new ExcelTypeException(productRecord.getName() + Exception.EXCEL_NAME_TOO_LONG.getValue());
        }
        if (productRecord.getCode().length() > 25) {
            log.error("产品:{}编码过长，请控制在25个字符以内", productRecord.getName());
            throw new ExcelTypeException(productRecord.getName() + Exception.EXCEL_CODE_TOO_LONG.getValue());
        }
        QueryWrapper<AccountInfo> accountInfoQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Classification> classificationMapperQueryWrapper = new QueryWrapper<>();
        QueryWrapper<Enterprise> enterpriseMapperQueryWrapper = new QueryWrapper<>();
        accountInfoQueryWrapper.eq("name", productRecord.getOperator());
        classificationMapperQueryWrapper.eq("name", productRecord.getClassification());
        enterpriseMapperQueryWrapper
                .eq("name", productRecord.getEnterprise())
                .and(code -> code.eq("social_code", productRecord.getSocialCode()));
        if (accountInfoMapper.selectList(accountInfoQueryWrapper).isEmpty()) {
            log.error("操作者未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_OPERATOR.getValue());
        }
        if (classificationMapper.selectList(classificationMapperQueryWrapper).isEmpty()) {
            log.error("产品分类未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_CLASS.getValue());
        }
        if (enterpriseMapper.selectList(enterpriseMapperQueryWrapper).isEmpty()) {
            log.error("企业未在系统注册，无法导入!");
            throw new ExcelTypeException(Exception.EXCEL_ENTERPRISE_REGISTERED.getValue());
        }
        dataLine++;
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        if (headMap.size() != TABLE_STRUCT.size()) {
            log.error("产品备案表头数量不匹配，期望 {} 个，实际 {} 个", TABLE_STRUCT.size(), headMap.size());
            throw new ExcelStructException(Exception.EXCEL_STRUCT_HEAD_NUM.getValue());
        }
        if (!headMap.values().containsAll(TABLE_STRUCT)) {
            log.error("产品备案表头不匹配，缺失的表头有: {}", TABLE_STRUCT.stream()
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
            log.error("产品备案表无数据，无法导入!");
            throw new ExcelNullException(Exception.EXCEL_NULL.getValue());
        }
    }
}
