package edu.hrbu.trace_backend_business.service.impl;

import cn.hutool.core.date.DateTime;
import edu.hrbu.trace_backend_business.entity.OnlineContext;
import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.*;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.enums.Message;
import edu.hrbu.trace_backend_business.entity.po.Account;
import edu.hrbu.trace_backend_business.entity.po.ImportantEnterprise;
import edu.hrbu.trace_backend_business.mapper.AccountMapper;
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.ImportantEnterpriseMapper;
import edu.hrbu.trace_backend_business.report.CommonReportFactory;
import edu.hrbu.trace_backend_business.service.AnalysisService;
import edu.hrbu.trace_backend_business.strategy.*;
import edu.hrbu.trace_backend_business.strategy.context.*;
import edu.hrbu.trace_backend_business.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class AnalysisServiceImpl implements AnalysisService {

    @Resource
    private ImportantEnterpriseMapper importantEnterpriseMapper;
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private MarketOperationContext marketOperationContext;
    @Resource
    private SupermarketAnalysisContext superMarketAnalysisContext;
    @Resource
    private BatchAnalysisContext batchAnalysisContext;
    @Resource
    private ProcessAnalysisContext processAnalysisContext;
    @Resource
    private AnimalAnalysisContext animalAnalysisContext;
    @Resource
    private ButchAnalysisContext butchAnalysisContext;
    @Resource
    private FarmAnalysisContext farmAnalysisContext;
    @Resource
    private PlantAnalysisContext plantAnalysisContext;
    @Resource
    private StructAnalysisContext structAnalysisContext;
    @Resource
    private TraceReportListContext traceReportListContext;
    @Resource
    private CommonReportFactory commonReportFactory;

    @Override
    public Result requestOperationsInfo(OperationsQuery query) {
        List<Map<String, Integer>> originData = getOperationsOriginData(query);
        List<Map<String, Double>> YOYData = getOperationsYOYData(query, originData);
        List<Map<String, Double>> QOQData = getOperationsQOQData(query, originData);
        return Result.ok(Message.GET_MARKET_OPERATIONS.getValue()).data("operations", Operations.builder()
                .originData(originData)
                .YOYData(YOYData)
                .QOQData(QOQData).build());
    }

    @Override
    public Result requestSupermarketInfo(MarketQuery query) {
        SupermarketAnalysisStrategy strategy = superMarketAnalysisContext.getSuperMarketAnalysisStrategy(
                query.getType()
        );
        return Result
                .ok(Message.GET_SUPERMARKET_ANALYSIS.getValue())
                .data("supermarket", strategy.getSuperMarketAnalysisData(query));
    }

    @Override
    public Result requestBatchInfo(BatchQuery query) {
        BatchAnalysisStrategy strategy = batchAnalysisContext.getBatchAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_BATCH_ANALYSIS.getValue())
                .data("batch", strategy.getBatchAnalysisData(query));
    }

    @Override
    public Result requestProcessInfo(ProcessQuery query) {
        ProcessAnalysisStrategy strategy = processAnalysisContext.getProcessAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_PROCESS_ANALYSIS.getValue())
                .data("process", strategy.getProcessAnalysisData(query));
    }

    @Override
    public Result requestAnimalInfo(AnimalQuery query) {
        AnimalAnalysisStrategy strategy = animalAnalysisContext.getAnimalAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_ANIMAL_ANALYSIS.getValue())
                .data("animal", strategy.getAnimalAnalysisData(query));
    }

    @Override
    public Result requestButchInfo(ButchQuery query) {
        ButchAnalysisStrategy strategy = butchAnalysisContext.getButchAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_BUTCH_ANALYSIS.getValue())
                .data("butch", strategy.getButchAnalysisData(query));
    }

    @Override
    public Result requestFarmInfo(FarmQuery query) {
        FarmAnalysisStrategy strategy = farmAnalysisContext.getFarmAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_FARM_ANALYSIS.getValue())
                .data("farm", strategy.getFarmAnalysisData(query));
    }

    @Override
    public Result requestPlantInfo(PlantQuery query) {
        PlantAnalysisStrategy strategy = plantAnalysisContext.getPlantAnalysisStrategy(query.getType());
        return Result
                .ok(Message.GET_PLANT_ANALYSIS.getValue())
                .data("plant", strategy.getPlantAnalysisData(query));
    }

    @Override
    public Result requestStructInfo(StructQuery query) {
        StructAnalysisStrategy strategy = structAnalysisContext.getStructAnalysisStrategy(query.getType());
        Map<String, Object> data = new HashMap<>();
        data.put("industry", strategy.getStructAnalysisDataList(query));
        data.put("report", strategy.getStructAnalysisReport(query));
        return Result
                .ok(Message.GET_STRUCT_ANALYSIS.getValue())
                .data("struct", data);
    }

    @Override
    public Result requestGenerateStructReport(ReportQuery query) {
        return commonReportFactory
                .getReportFactory(query.getReport())
                .createReportSession(query)
                .generateReport(query);
    }

    @Override
    public Result requestGetStructReport(ReportQuery query) {
        return commonReportFactory
                .getReportFactory(query.getReport())
                .createReportSession(query)
                .reviewReport(query);
    }

    @Override
    public Result requestImportantEnterpriseData() {
        Map<String, List<ImportantEnterprise>> data = new HashMap<>();
        List<ImportantEnterprise> plant = processImportantEnterpriseData(EnterpriseType.PLANT.getValue());
        List<ImportantEnterprise> animal = processImportantEnterpriseData(EnterpriseType.ANIMAL.getValue());
        List<ImportantEnterprise> butch = processImportantEnterpriseData(EnterpriseType.BUTCH.getValue());
        List<ImportantEnterprise> farm = processImportantEnterpriseData(EnterpriseType.FARM.getValue());
        List<ImportantEnterprise> process = processImportantEnterpriseData(EnterpriseType.PRODUCT.getValue());
        List<ImportantEnterprise> batch = processImportantEnterpriseData(EnterpriseType.BATCH.getValue());
        List<ImportantEnterprise> market = processImportantEnterpriseData(EnterpriseType.SHOP.getValue());
        data.put("plant", plant);
        data.put("animal", animal);
        data.put("butch", butch);
        data.put("farm", farm);
        data.put("process", process);
        data.put("batch", batch);
        data.put("market", market);
        return Result
                .ok(Message.GET_IMPORTANT_SUCCESS.getValue())
                .data("important", data);
    }

    @Override
    public Result requestAddNewImportantEnterprise(Enterprise enterprise) {
        ImportantEnterprise importantEnterprise = importantEnterpriseMapper.selectById(enterprise.getEid());
        if (importantEnterprise != null) {
            return Result.fail(Message.ADD_IMPORTANT_ERROR.getValue());
        }
        DateTime operateTime = new DateTime(DateTime.now());
        int insert = importantEnterpriseMapper.insert(
                ImportantEnterprise.builder()
                        .eid(enterprise.getEid())
                        .oid(Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject()))
                        .date(operateTime.toString(Format.FULL_TIME_FORMAT.getValue())).build()
        );
        return insert != 0 ?
                Result.ok(Message.ADD_IMPORTANT_SUCCESS.getValue()) :
                Result.fail(Message.ADD_IMPORTANT_FAIL.getValue());
    }

    @Override
    public Result requestDeleteImportantEnterprise() {
        Integer operator = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        Account account = accountMapper.selectById(operator);
        // TODO 根据是否是指定角色判断是否有权限删除所有记录,角色不确定
        if (account.getRid() != 1) {
            Result.fail(Message.NO_AUTH.getValue());
        }
        importantEnterpriseMapper.delete(null);
        return Result.ok(Message.DELETE_IMPORTANT.getValue());
    }

    @Override
    public Result requestDeleteImportantEnterprise(Enterprise enterprise) {
        int delete = importantEnterpriseMapper.deleteById(enterprise.getEid());
        return delete != 0 ?
                Result.ok(Message.DELETE_IMPORTANT.getValue()) :
                Result.fail(Message.DELETE_IMPORTANT_FAIL.getValue());
    }

    @Override
    public Result requestDeleteImportantEnterprise(Integer[] range) {
        int delete = 0;
        for (Integer eid : range) {
            delete += importantEnterpriseMapper.deleteById(eid);
        }
        return delete == range.length ?
                Result.ok(Message.DELETE_IMPORTANT.getValue()) :
                Result.ok(Message.DELETE_IMPORTANT_BATCH.getValue());
    }

    @Override
    public Result requestGetTraceReportList(TraceQuery query) {
        Map<String, Object> traceReportList = traceReportListContext
                .getTraceReportListStrategy(query.getType())
                .getTraceReportList(query);
        return Result
                .ok(Message.GET_STRUCT_REPORT_SUCCESS.getValue())
                .data("trace", traceReportList);
    }

    @Override
    public Result requestGetTraceReport(ReportQuery query) {
        return commonReportFactory
                .getReportFactory(query.getReport())
                .createReportSession(query)
                .reviewReport(query);
    }

    private List<Map<String, Integer>> getOperationsOriginData(OperationsQuery query) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationOriginData(query);
    }

    private List<Map<String, Double>> getOperationsYOYData(OperationsQuery query, List<Map<String, Integer>> origin) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationYOYData(query, origin);
    }

    private List<Map<String, Double>> getOperationsQOQData(OperationsQuery query, List<Map<String, Integer>> origin) {
        MarketOperationStrategy strategy = marketOperationContext.getMarketOperationStrategy(query.getType());
        return strategy.getMarketOperationQOQData(query, origin);
    }

    private List<ImportantEnterprise> processImportantEnterpriseData(Integer type) {
        List<ImportantEnterprise> data = importantEnterpriseMapper.selectImportantList(type);
        if (!data.isEmpty()) {
            for (ImportantEnterprise important : data) {
                important.setEnterprise(enterpriseMapper.selectById(important.getEid()).getName());
                important.setOperator(accountMapper.selectById(important.getOid()).getUsername());
            }
        }
        return data;
    }

}
