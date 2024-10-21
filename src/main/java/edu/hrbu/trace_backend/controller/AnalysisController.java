package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.analysis.BatchQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.MarketQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.OperationsQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.ProcessQuery;
import edu.hrbu.trace_backend.service.AnalysisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
