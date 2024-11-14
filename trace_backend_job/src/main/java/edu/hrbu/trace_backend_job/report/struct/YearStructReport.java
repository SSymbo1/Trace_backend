package edu.hrbu.trace_backend_job.report.struct;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.EnterpriseTypeCount;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ReportQuery;
import edu.hrbu.trace_backend_job.entity.dto.analysis.StructData;
import edu.hrbu.trace_backend_job.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_job.entity.enums.Format;
import edu.hrbu.trace_backend_job.entity.enums.Message;
import edu.hrbu.trace_backend_job.entity.po.Enterprise;
import edu.hrbu.trace_backend_job.entity.po.ImportantEnterprise;
import edu.hrbu.trace_backend_job.entity.po.StructReport;
import edu.hrbu.trace_backend_job.entity.po.json.PointFocus;
import edu.hrbu.trace_backend_job.entity.po.json.PointRate;
import edu.hrbu.trace_backend_job.entity.po.json.PointStruct;
import edu.hrbu.trace_backend_job.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_job.mapper.ImportantEnterpriseMapper;
import edu.hrbu.trace_backend_job.mapper.StatisticsMapper;
import edu.hrbu.trace_backend_job.mapper.StructReportMapper;
import edu.hrbu.trace_backend_job.report.Report;
import edu.hrbu.trace_backend_job.strategy.impl.struct.YearStructOperation;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("YearStruct")
public class YearStructReport implements Report {

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private StructReportMapper structReportMapper;
    @Resource
    private StatisticsMapper statisticsMapper;
    @Resource
    private ImportantEnterpriseMapper importantEnterpriseMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Result generateReport(ReportQuery query) {
        String annotation = applicationContext.getBeanNamesForType(YearStructOperation.class)[0];
        Integer reportCount = structReportMapper.selectStructReportCount(query.getDate(), query.getType());
        if (reportCount != 0) {
            return Result
                    .ok(Message.ALREADY_HAVE_REPORT.getValue())
                    .data(
                            "report",
                            structReportMapper.selectStructReportList(query.getDate(), annotation)
                    );
        }
        DateTime operateTime = new DateTime(DateTime.now());
        Map<String, Integer> commonTypeCountMap = new HashMap<>();
        Map<String, Integer> focusTypeCountMap = new HashMap<>();
        List<PointStruct> pointStructList = new ArrayList<>();
        List<PointFocus> pointFocusList = new ArrayList<>();
        List<PointRate> pointRateList = new ArrayList<>();
        List<ImportantEnterprise> focusEnterpriseList = importantEnterpriseMapper.selectList(null);
        for (EnterpriseType initEnum : EnterpriseType.values()) {
            commonTypeCountMap.put(initEnum.getKey(), 0);
            focusTypeCountMap.put(initEnum.getKey(), 0);
        }
        for (EnterpriseType typeEnum : EnterpriseType.values()) {
            commonTypeCountMap.put(
                    typeEnum.getKey(),
                    statisticsMapper.selectEnterpriseTypeCount(typeEnum.getValue())
            );
        }
        if (!focusEnterpriseList.isEmpty()) {
            for (ImportantEnterprise focus : focusEnterpriseList) {
                Enterprise enterprise = enterpriseMapper.selectById(focus.getEid());
                focusTypeCountMap.put(
                        EnterpriseType.getEnterpriseTypeByValue(enterprise.getIlk())
                                .get()
                                .getKey(),
                        focusTypeCountMap.get(EnterpriseType.getEnterpriseTypeByValue(enterprise.getIlk())
                                .get()
                                .getKey()) + 1
                );
                StructData structData = statisticsMapper.selectStructTotalStatisticsData(
                        query.getDate(),
                        focus.getEid(),
                        annotation
                );
                Integer commonTypeTotal = statisticsMapper.selectReportStatisticsCountByCondition(
                        query.getDate(),
                        query.getType(),
                        enterprise.getIlk(),
                        null
                );
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
                Integer traceCountTotal = statisticsMapper.selectTraceDataCount();
                pointStructList.add(PointStruct.builder()
                        .name(structData.getName())
                        .total(String.valueOf(structData.getTotal()))
                        .type(String.valueOf(structData.getType()))
                        .qoq(String.valueOf(qoqTotal))
                        .yoy(String.valueOf(yoyTotal))
                        .build());
                pointFocusList.add(PointFocus.builder()
                        .name(structData.getName())
                        .type(String.valueOf(enterprise.getIlk()))
                        .total(
                                traceCountTotal == 0 ?
                                        NumberUtil.decimalFormat("#.##%", 0) :
                                        NumberUtil.decimalFormat(
                                                "#.##%",
                                                (double) structData.getTotal() / traceCountTotal
                                        )
                        ).build());
                pointRateList.add(PointRate.builder()
                        .name(enterprise.getName())
                        .type(String.valueOf(enterprise.getIlk()))
                        .total(
                                commonTypeTotal == 0 ?
                                        NumberUtil.decimalFormat("#.##%", 0) :
                                        NumberUtil.decimalFormat(
                                                "#.##%",
                                                (double) structData.getTotal() / commonTypeTotal
                                        )
                        ).build());
            }
        }
        StructReport structReport = StructReport.builder()
                .generate(operateTime.toString(Format.FULL_TIME_FORMAT.getValue()))
                .date(query.getDate())
                .plantTotal(commonTypeCountMap.get(EnterpriseType.PLANT.getKey()))
                .batchTotal(commonTypeCountMap.get(EnterpriseType.BATCH.getKey()))
                .butchTotal(commonTypeCountMap.get(EnterpriseType.BUTCH.getKey()))
                .farmTotal(commonTypeCountMap.get(EnterpriseType.FARM.getKey()))
                .marketTotal(commonTypeCountMap.get(EnterpriseType.SHOP.getKey()))
                .processTotal(commonTypeCountMap.get(EnterpriseType.PRODUCT.getKey()))
                .animalTotal(commonTypeCountMap.get(EnterpriseType.ANIMAL.getKey()))
                .plantPoint(focusTypeCountMap.get(EnterpriseType.PLANT.getKey()))
                .batchPoint(focusTypeCountMap.get(EnterpriseType.BATCH.getKey()))
                .butchPoint(focusTypeCountMap.get(EnterpriseType.BUTCH.getKey()))
                .farmPoint(focusTypeCountMap.get(EnterpriseType.FARM.getKey()))
                .marketPoint(focusTypeCountMap.get(EnterpriseType.SHOP.getKey()))
                .processPoint(focusTypeCountMap.get(EnterpriseType.PRODUCT.getKey()))
                .animalPoint(focusTypeCountMap.get(EnterpriseType.ANIMAL.getKey()))
                .pointStruct(pointStructList)
                .pointFocus(pointFocusList)
                .pointRate(pointRateList)
                .type(2)
                .build();
        int insert = structReportMapper.insert(structReport);
        return insert != 0 ?
                Result.ok(Message.STRUCT_REPORT_SUCCESS.getValue()) :
                Result.fail(Message.STRUCT_REPORT_FAIL.getValue());
    }

    @Override
    public Result reviewReport(ReportQuery query) {
        StructReport structReport = structReportMapper.selectOne(
                new QueryWrapper<StructReport>()
                        .eq("date", query.getDate())
                        .and(type -> type.eq("type", 2))
        );
        List<EnterpriseTypeCount> countList = new ArrayList<>();
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.SHOP.getKey())
                .normal(structReport.getMarketTotal())
                .focus(structReport.getMarketPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.PRODUCT.getKey())
                .normal(structReport.getProcessTotal())
                .focus(structReport.getProcessPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.BATCH.getKey())
                .normal(structReport.getBatchTotal())
                .focus(structReport.getBatchPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.ANIMAL.getKey())
                .normal(structReport.getAnimalTotal())
                .focus(structReport.getAnimalPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.FARM.getKey())
                .normal(structReport.getFarmTotal())
                .focus(structReport.getFarmPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.PLANT.getKey())
                .normal(structReport.getPlantTotal())
                .focus(structReport.getPlantPoint()).build());
        countList.add(EnterpriseTypeCount.builder()
                .name(EnterpriseType.BUTCH.getKey())
                .normal(structReport.getButchTotal())
                .focus(structReport.getButchPoint()).build());
        Map<String, Object> reportData = new HashMap<>();
        reportData.put("countData", countList);
        reportData.put("focusData", structReport.getPointStruct());
        reportData.put("focusRatio", structReport.getPointFocus());
        reportData.put("focusRate", structReport.getPointRate());
        return Result
                .ok(Message.GET_STRUCT_REPORT_SUCCESS.getValue())
                .data("report", reportData);
    }
}
