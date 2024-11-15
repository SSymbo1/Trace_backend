package edu.hrbu.trace_backend_business.strategy.impl.struct;

import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.dto.analysis.Struct;
import edu.hrbu.trace_backend_business.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend_business.entity.dto.analysis.StructQuery;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import edu.hrbu.trace_backend_business.entity.po.StructReport;
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.StatisticsMapper;
import edu.hrbu.trace_backend_business.mapper.StructReportMapper;
import edu.hrbu.trace_backend_business.strategy.StructAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("StructDay")
public class DayStructOperation implements StructAnalysisStrategy {

    @Resource
    private StructReportMapper structReportMapper;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Struct getStructAnalysisDataList(StructQuery query) {
        Map<String, List<StructData>> tempMap = new HashMap<>();
        for (EnterpriseType enterpriseType : EnterpriseType.values()) {
            tempMap.put(enterpriseType.getKey(), new ArrayList<>());
        }
        for (EnterpriseType enterpriseType : EnterpriseType.values()) {
            List<Enterprise> enterpriseList = enterpriseMapper.selectList(
                    new QueryWrapper<Enterprise>().eq("type", enterpriseType.getValue())
            );
            for (Enterprise enterprise : enterpriseList) {
                StructData structData = statisticsMapper.selectStructTotalStatisticsData(
                        query.getDate(), enterprise.getEid(), query.getType()
                );
                List<StructData> tempList = tempMap.get(enterpriseType.getKey());
                tempList.add(structData);
                tempMap.put(
                        enterpriseType.getKey(),
                        tempList
                );
            }
        }
        int total = tempMap.values().stream()
                .flatMap(List::stream)
                .mapToInt(StructData::getTotal)
                .sum();
        if (total != 0) {
            tempMap.forEach((key, value) -> value.forEach(data -> data.setRate(
                    NumberUtil.decimalFormat("#.##%", (double) data.getTotal() / total)
            )));
        }
        return Struct.builder()
                .total(total)
                .product(tempMap.get(EnterpriseType.PRODUCT.getKey()))
                .plant(tempMap.get(EnterpriseType.PLANT.getKey()))
                .butch(tempMap.get(EnterpriseType.BUTCH.getKey()))
                .farm(tempMap.get(EnterpriseType.FARM.getKey()))
                .batch(tempMap.get(EnterpriseType.BATCH.getKey()))
                .market(tempMap.get(EnterpriseType.SHOP.getKey()))
                .animal(tempMap.get(EnterpriseType.ANIMAL.getKey())).build();
    }

    @Override
    public List<StructReport> getStructAnalysisReport(StructQuery query) {
        return structReportMapper.selectStructReportList(query.getDate(), query.getType());
    }
}
