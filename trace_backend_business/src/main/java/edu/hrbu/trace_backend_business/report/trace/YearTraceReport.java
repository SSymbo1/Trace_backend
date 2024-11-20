package edu.hrbu.trace_backend_business.report.trace;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_business.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import edu.hrbu.trace_backend_business.entity.po.ImportantEnterprise;
import edu.hrbu.trace_backend_business.entity.po.json.AreaData;
import edu.hrbu.trace_backend_business.entity.po.json.PointStruct;
import edu.hrbu.trace_backend_business.entity.po.json.TraceDataCollect;
import edu.hrbu.trace_backend_business.mapper.*;
import edu.hrbu.trace_backend_business.report.Report;
import edu.hrbu.trace_backend_business.strategy.impl.struct.YearStructOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("YearTrace")
public class YearTraceReport implements Report {

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private YearTraceReportMapper yearTraceReportMapper;
    @Resource
    private ImportantEnterpriseMapper importantEnterpriseMapper;

    @Override
    public Result generateReport(ReportQuery query) {
        DateTime date = new DateTime(DateTime.now());
        String annotation = applicationContext.getBeanNamesForType(YearStructOperation.class)[0];
        List<PointStruct> pointStructList = new ArrayList<>();
        Map<String, Integer> areaCount = new HashMap<>();
        List<AreaData> areaData = new ArrayList<>();
        for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
            areaCount.put(province.getKey(), province.getValue());
        }
        TraceDataCollect butchApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.BUTCH.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.BUTCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.BUTCH.getValue(), "approach", query.getType()
                ));
        TraceDataCollect butchEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.BUTCH.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.BUTCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.BUTCH.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect batchApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.BATCH.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.BATCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.BATCH.getValue(), "approach", query.getType()
                ));
        TraceDataCollect batchEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.BATCH.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.BATCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.BATCH.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect shopApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.SHOP.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.SHOP.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.SHOP.getValue(), "approach", query.getType()
                ));
        TraceDataCollect shopEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.SHOP.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.SHOP.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.SHOP.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect plantApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.PLANT.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.PLANT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.PLANT.getValue(), "approach", query.getType()
                ));
        TraceDataCollect plantEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.PLANT.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.PLANT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.PLANT.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect productApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.PRODUCT.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.PRODUCT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.PRODUCT.getValue(), "approach", query.getType()
                ));
        TraceDataCollect productEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.PRODUCT.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.PRODUCT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.PRODUCT.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect farmApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.FARM.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.FARM.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.FARM.getValue(), "approach", query.getType()
                ));
        TraceDataCollect farmEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.FARM.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.FARM.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.FARM.getValue(), "entrance", query.getType()
                ));
        TraceDataCollect animalApproach = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.ANIMAL.getValue(), "approach", query.getType()
                ).setName(EnterpriseType.ANIMAL.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.ANIMAL.getValue(), "approach", query.getType()
                ));
        TraceDataCollect animalEntrance = statisticsMapper.selectTraceDataCollect(
                        query.getDate(), EnterpriseType.ANIMAL.getValue(), "entrance", query.getType()
                ).setName(EnterpriseType.ANIMAL.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByCondition(
                        query.getDate(), EnterpriseType.ANIMAL.getValue(), "entrance", query.getType()
                ));
        List<ImportantEnterprise> focusEnterpriseList = importantEnterpriseMapper.selectList(null);
        if (!focusEnterpriseList.isEmpty()) {
            for (ImportantEnterprise focus : focusEnterpriseList) {
                Enterprise enterprise = enterpriseMapper.selectById(focus.getEid());
                Integer yoyTotal = statisticsMapper.selectReportStatisticsYOYByCondition(
                        query.getDate(),
                        query.getType(),
                        enterprise.getIlk(),
                        enterprise.getEid()
                );
                Integer qoqTotal = statisticsMapper.selectReportStatisticsQOQByCondition(
                        query.getDate(),
                        query.getType(),
                        enterprise.getIlk(),
                        enterprise.getEid()
                );
                StructData structData = statisticsMapper.selectStructTotalStatisticsData(
                        query.getDate(),
                        focus.getEid(),
                        annotation
                );
                pointStructList.add(PointStruct.builder()
                        .name(structData.getName())
                        .total(String.valueOf(structData.getTotal()))
                        .type(String.valueOf(structData.getType()))
                        .qoq(String.valueOf(qoqTotal))
                        .yoy(String.valueOf(yoyTotal))
                        .build());
            }
        }
        List<String> addressList = statisticsMapper.selectTraceAddressByDateAndType(
                query.getDate(), query.getType()
        );
        if (!addressList.isEmpty()) {
            addressList.forEach(address -> {
                for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
                    if (address.contains(province.getKey())) {
                        areaCount.put(province.getKey(), areaCount.get(province.getKey()) + 1);
                    }
                }
            });
            areaCount.forEach((area, count) -> {
                AreaData data = AreaData.builder()
                        .name(area)
                        .total(count).build();
                areaData.add(data);
            });
        }
        edu.hrbu.trace_backend_business.entity.po.YearTraceReport report = edu.hrbu.trace_backend_business.entity.po.YearTraceReport.builder()
                .operateDate(date.toString(Format.FULL_TIME_FORMAT.getValue()))
                .date(query.getDate())
                .animalApproach(animalApproach)
                .animalEntrance(animalEntrance)
                .batchApproach(batchApproach)
                .batchEntrance(batchEntrance)
                .butchApproach(butchApproach)
                .butchEntrance(butchEntrance)
                .farmApproach(farmApproach)
                .farmEntrance(farmEntrance)
                .plantApproach(plantApproach)
                .plantEntrance(plantEntrance)
                .marketApproach(shopApproach)
                .marketEntrance(shopEntrance)
                .processApproach(productApproach)
                .processEntrance(productEntrance)
                .focusEnterpriseData(pointStructList)
                .areaData(areaData).build();
        int insert = yearTraceReportMapper.insert(report);
        return insert != 0 ?
                Result.ok(query.getDate() + "年溯源报告生成成功!") :
                Result.fail(query.getDate() + "年溯源报告生成失败!");
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        Map<String, Object> data = new HashMap<>();
        edu.hrbu.trace_backend_business.entity.po.YearTraceReport yearTraceReport = yearTraceReportMapper.selectOne(
                new QueryWrapper<edu.hrbu.trace_backend_business.entity.po.YearTraceReport>()
                        .eq("date", query.getDate())
        );
        List<TraceDataCollect> approach = new ArrayList<>();
        List<TraceDataCollect> entrance = new ArrayList<>();
        approach.add(yearTraceReport.getAnimalApproach());
        approach.add(yearTraceReport.getBatchApproach());
        approach.add(yearTraceReport.getButchApproach());
        approach.add(yearTraceReport.getFarmApproach());
        approach.add(yearTraceReport.getPlantApproach());
        approach.add(yearTraceReport.getMarketApproach());
        approach.add(yearTraceReport.getProcessApproach());
        entrance.add(yearTraceReport.getAnimalEntrance());
        entrance.add(yearTraceReport.getBatchEntrance());
        entrance.add(yearTraceReport.getButchEntrance());
        entrance.add(yearTraceReport.getFarmEntrance());
        entrance.add(yearTraceReport.getPlantEntrance());
        entrance.add(yearTraceReport.getMarketEntrance());
        entrance.add(yearTraceReport.getProcessEntrance());
        data.put("approach", approach);
        data.put("entrance", entrance);
        data.put("enterprise", yearTraceReport.getFocusEnterpriseData());
        data.put("area", yearTraceReport.getAreaData());
        return Result
                .ok(Message.GET_TRACE_YEAR_REPORT.getValue())
                .data("trace", data);
    }
}
