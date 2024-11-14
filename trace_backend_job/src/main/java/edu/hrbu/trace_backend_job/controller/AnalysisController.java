package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.analysis.*;
import edu.hrbu.trace_backend_job.service.AnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "追溯数据分析数据接口")
@RequestMapping("/analysis")
public class AnalysisController {

    @Resource
    private AnalysisService analysisService;

    @GetMapping("/operations")
    @ApiOperation(
            value = "市场运行分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段市场运行分析数据"
    )
    Result getOperationsData(@Validated OperationsQuery query) {
        return analysisService.requestOperationsInfo(query);
    }

    @GetMapping("/supermarket")
    @ApiOperation(
            value = "超市数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段超市数据分析数据"
    )
    Result getSupermarketAnalysisData(@Validated MarketQuery query) {
        return analysisService.requestSupermarketInfo(query);
    }

    @GetMapping("/batch")
    @ApiOperation(
            value = "批发数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段批发数据分析数据"
    )
    Result getBatchAnalysisData(@Validated BatchQuery query) {
        return analysisService.requestBatchInfo(query);
    }

    @GetMapping("/process")
    @ApiOperation(
            value = "加工数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段加工数据分析数据"
    )
    Result getProcessAnalysisData(@Validated ProcessQuery query) {
        return analysisService.requestProcessInfo(query);
    }

    @GetMapping("/plant")
    @ApiOperation(
            value = "种植数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段种植数据分析数据"
    )
    Result getPlantAnalysisData(@Validated PlantQuery query) {
        return analysisService.requestPlantInfo(query);
    }

    @GetMapping("/farm")
    @ApiOperation(
            value = "农贸数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段农贸数据分析数据"
    )
    Result getFarmAnalysisData(@Validated FarmQuery query) {
        return analysisService.requestFarmInfo(query);
    }

    @GetMapping("/animal")
    @ApiOperation(
            value = "养殖数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段养殖数据分析数据"
    )
    Result getAnimalAnalysisData(@Validated AnimalQuery query) {
        return analysisService.requestAnimalInfo(query);
    }

    @GetMapping("/butch")
    @ApiOperation(
            value = "屠宰数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段屠宰数据分析数据"
    )
    Result getButchAnalysisData(@Validated ButchQuery query) {
        return analysisService.requestButchInfo(query);
    }

    @GetMapping("/struct")
    @ApiOperation(
            value = "行业结构数据分析数据接口",
            notes = "根据提供查询的方式，返回指定时间段行业结构数据分析数据"
    )
    Result getStructAnalysisData(@Validated StructQuery query) {
        return analysisService.requestStructInfo(query);
    }

    @GetMapping("/struct/important")
    @ApiOperation(
            value = "获取行业结构重要企业信息接口",
            notes = "调用此接口后，返回所有行业结构重要企业信息"
    )
    Result getStructImportantEnterpriseData() {
        return analysisService.requestImportantEnterpriseData();
    }

    @PostMapping("/struct/important")
    @ApiOperation(
            value = "添加行业结构重要企业信息接口",
            notes = "调用此接口后，根据表单的企业信息添加重要企业信息数据"
    )
    Result addStructImportantEnterprise(@RequestBody Enterprise enterprise) {
        return analysisService.requestAddNewImportantEnterprise(enterprise);
    }

    @PutMapping("/struct/important/remove")
    @ApiOperation(
            value = "删除行业结构重要企业信息接口",
            notes = "调用此接口后，删除指定的行业结构重要企业信息数据"
    )
    Result deleteStructImportantEnterprise(@RequestBody Enterprise enterprise) {
        return analysisService.requestDeleteImportantEnterprise(enterprise);
    }

    @PutMapping("/struct/important/batch")
    @ApiOperation(
            value = "批量删除行业结构重要企业信息接口",
            notes = "调用此接口后，根据回传的范围数组，批量删除行业结构重要企业信息数据"
    )
    Result deleteStructImportantEnterpriseRanged(@RequestBody Integer[] range) {
        return analysisService.requestDeleteImportantEnterprise(range);
    }

    @PutMapping("/struct/important/clear")
    @ApiOperation(
            value = "删除所有行业结构重要企业信息接口",
            notes = "调用此接口后，删除所有行业结构重要企业信息数据"
    )
    Result deleteStructImportantEnterpriseAll() {
        return analysisService.requestDeleteImportantEnterprise();
    }

    @PostMapping("/struct/report")
    @ApiOperation(
            value = "生成行业结构分析报告接口",
            notes = "调用此接口后，根据表单的信息生成指定时间段内的行业结构分析报告"
    )
    Result generateStructAnalysisReport(@RequestBody ReportQuery query) {
        return analysisService.requestGenerateStructReport(query);
    }

    @GetMapping("/struct/report")
    @ApiOperation(
            value = "获取行业结构分析报告数据接口",
            notes = "调用此接口后，根据提供得查询参数返回指定日期构建行业结构分析报告所需的数据"
    )
    Result getStructAnalysisReportData(ReportQuery query) {
        return analysisService.requestGetStructReport(query);
    }

    @GetMapping("/trace")
    @ApiOperation(
            value = "获取追溯报告列表接口",
            notes = "调用此接口后，根据提供的查询参数返回指定时间段的追溯报告列表"
    )
    Result getTraceDataList(ReportQuery query) {
        return analysisService.requestGetTraceReportList(query);
    }


}
