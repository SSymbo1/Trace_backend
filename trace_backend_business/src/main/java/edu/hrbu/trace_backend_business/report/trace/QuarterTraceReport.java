package edu.hrbu.trace_backend_business.report.trace;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
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
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.ImportantEnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.QuarterTraceReportMapper;
import edu.hrbu.trace_backend_business.mapper.StatisticsMapper;
import edu.hrbu.trace_backend_business.report.Report;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component("QuarterTrace")
public class QuarterTraceReport implements Report {

    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ImportantEnterpriseMapper importantEnterpriseMapper;
    @Resource
    private QuarterTraceReportMapper quarterTraceReportMapper;

    @Override
    public Result generateReport(ReportQuery query) {
        DateTime date = new DateTime(DateTime.now());
        List<PointStruct> pointStructList = new ArrayList<>();
        int quarter = DateUtil.quarter(date);
        String quarterStartDate = DateUtil.format(DateUtil.beginOfQuarter(date), Format.YEAR_MONTH_FORMAT.getValue());
        String quarterEndDate = DateUtil.format(DateUtil.endOfQuarter(date), Format.YEAR_MONTH_FORMAT.getValue());
        log.info("生成第{}季度溯源报告", quarter);
        Map<String, Integer> areaCount = new HashMap<>();
        List<AreaData> areaData = new ArrayList<>();
        for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
            areaCount.put(province.getKey(), province.getValue());
        }
        TraceDataCollect butchApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BUTCH.getValue(), "approach"
                ).setName(EnterpriseType.BUTCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BUTCH.getValue(), "approach"
                ));
        TraceDataCollect butchEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BUTCH.getValue(), "entrance"
                ).setName(EnterpriseType.BUTCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BUTCH.getValue(), "entrance"
                ));
        TraceDataCollect batchApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BATCH.getValue(), "approach"
                ).setName(EnterpriseType.BATCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BATCH.getValue(), "approach"
                ));
        TraceDataCollect batchEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BATCH.getValue(), "entrance"
                ).setName(EnterpriseType.BATCH.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.BATCH.getValue(), "entrance"
                ));
        TraceDataCollect shopApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.SHOP.getValue(), "approach"
                ).setName(EnterpriseType.SHOP.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.SHOP.getValue(), "approach"
                ));
        TraceDataCollect shopEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.SHOP.getValue(), "entrance"
                ).setName(EnterpriseType.SHOP.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.SHOP.getValue(), "entrance"
                ));
        TraceDataCollect plantApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PLANT.getValue(), "approach"
                ).setName(EnterpriseType.PLANT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PLANT.getValue(), "approach"
                ));
        TraceDataCollect plantEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PLANT.getValue(), "entrance"
                ).setName(EnterpriseType.PLANT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PLANT.getValue(), "entrance"
                ));
        TraceDataCollect productApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PRODUCT.getValue(), "approach"
                ).setName(EnterpriseType.PRODUCT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PRODUCT.getValue(), "approach"
                ));
        TraceDataCollect productEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PRODUCT.getValue(), "entrance"
                ).setName(EnterpriseType.PRODUCT.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.PRODUCT.getValue(), "entrance"
                ));
        TraceDataCollect farmApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.FARM.getValue(), "approach"
                ).setName(EnterpriseType.FARM.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.FARM.getValue(), "approach"
                ));
        TraceDataCollect farmEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.FARM.getValue(), "entrance"
                ).setName(EnterpriseType.FARM.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.FARM.getValue(), "entrance"
                ));
        TraceDataCollect animalApproach = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.ANIMAL.getValue(), "approach"
                ).setName(EnterpriseType.ANIMAL.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.ANIMAL.getValue(), "approach"
                ));
        TraceDataCollect animalEntrance = statisticsMapper.selectTraceDataCollectQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.ANIMAL.getValue(), "entrance"
                ).setName(EnterpriseType.ANIMAL.getKey())
                .setPopular(statisticsMapper.selectTracePopularEnterpriseNameByConditionQuarter(
                        quarterStartDate, quarterEndDate, EnterpriseType.ANIMAL.getValue(), "entrance"
                ));
        List<ImportantEnterprise> focusEnterpriseList = importantEnterpriseMapper.selectList(null);
        if (!focusEnterpriseList.isEmpty()) {
            for (ImportantEnterprise focus : focusEnterpriseList) {
                Enterprise enterprise = enterpriseMapper.selectById(focus.getEid());
                Integer yoyTotal = statisticsMapper.selectReportStatisticsYOYByConditionQuarter(
                        quarterStartDate, quarterEndDate, enterprise.getIlk(), enterprise.getEid()
                );
                Integer qoqTotal = statisticsMapper.selectReportStatisticsQOQByConditionQuarter(
                        quarterStartDate, quarterEndDate, enterprise.getIlk(), enterprise.getEid()
                );
                StructData structData = statisticsMapper.selectStructTotalStatisticsDataQuarter(
                        quarterStartDate, quarterEndDate, focus.getEid()
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
        List<String> addressList = statisticsMapper.selectTraceAddressByDateAndTypeQuarter(
                quarterStartDate, quarterEndDate
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
        edu.hrbu.trace_backend_business.entity.po.QuarterTraceReport quarterTraceReport = edu.hrbu.trace_backend_business.entity.po.QuarterTraceReport.builder()
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
        int insert = quarterTraceReportMapper.insert(quarterTraceReport);
        return insert != 0 ?
                Result.ok(query.getDate() + "月第" + quarter + "季度溯源报告生成成功!") :
                Result.fail(query.getDate() + "月第" + quarter + "季度溯源报告生成失败!");
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        Map<String, Object> data = new HashMap<>();
        edu.hrbu.trace_backend_business.entity.po.QuarterTraceReport quarterTraceReport = quarterTraceReportMapper.selectOne(
                new QueryWrapper<edu.hrbu.trace_backend_business.entity.po.QuarterTraceReport>()
                        .eq("date", query.getDate())
        );
        List<TraceDataCollect> approach = new ArrayList<>();
        List<TraceDataCollect> entrance = new ArrayList<>();
        approach.add(quarterTraceReport.getAnimalApproach());
        approach.add(quarterTraceReport.getBatchApproach());
        approach.add(quarterTraceReport.getButchApproach());
        approach.add(quarterTraceReport.getFarmApproach());
        approach.add(quarterTraceReport.getPlantApproach());
        approach.add(quarterTraceReport.getMarketApproach());
        approach.add(quarterTraceReport.getProcessApproach());
        entrance.add(quarterTraceReport.getAnimalEntrance());
        entrance.add(quarterTraceReport.getBatchEntrance());
        entrance.add(quarterTraceReport.getButchEntrance());
        entrance.add(quarterTraceReport.getFarmEntrance());
        entrance.add(quarterTraceReport.getPlantEntrance());
        entrance.add(quarterTraceReport.getMarketEntrance());
        entrance.add(quarterTraceReport.getProcessEntrance());
        data.put("approach", approach);
        data.put("entrance", entrance);
        data.put("enterprise", quarterTraceReport.getFocusEnterpriseData());
        data.put("area", quarterTraceReport.getAreaData());
        return Result
                .ok(Message.GET_TRACE_MONTH_REPORT.getValue())
                .data("trace", data);
    }
}
