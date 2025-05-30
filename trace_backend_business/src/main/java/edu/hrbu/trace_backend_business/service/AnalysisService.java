package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.analysis.*;

public interface AnalysisService {

    Result requestOperationsInfo(OperationsQuery query);

    Result requestSupermarketInfo(MarketQuery query);

    Result requestBatchInfo(BatchQuery query);

    Result requestProcessInfo(ProcessQuery query);

    Result requestPlantInfo(PlantQuery query);

    Result requestFarmInfo(FarmQuery query);

    Result requestAnimalInfo(AnimalQuery query);

    Result requestButchInfo(ButchQuery query);

    Result requestStructInfo(StructQuery query);

    Result requestImportantEnterpriseData();

    Result requestAddNewImportantEnterprise(Enterprise enterprise);

    Result requestDeleteImportantEnterprise(Enterprise enterprise);

    Result requestDeleteImportantEnterprise(Integer[] range);

    Result requestDeleteImportantEnterprise();

    Result requestGenerateStructReport(ReportQuery query);

    Result requestGetStructReport(ReportQuery query);

    Result requestGetTraceReportList(TraceQuery query);

    Result requestGetTraceReport(ReportQuery query);

}
