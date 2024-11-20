package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.monitor.InfoQuery;
import edu.hrbu.trace_backend_business.entity.dto.monitor.SummaryQuery;
import edu.hrbu.trace_backend_business.service.MonitorService;
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
@Api(tags = "运行监测管理数据接口")
@RequestMapping("/monitor")
public class MonitorController {

    @Resource
    private MonitorService monitorService;

    @GetMapping("/summary/histogram")
    @ApiOperation(
            value = "采集数据汇总竖形柱状图数据集接口",
            notes = "采集数据汇总竖形柱状图数据接口，需要登录验证，" +
                    "提供日期范围返回指定时间段的数据，否则返回前15天的数据"
    )
    public Result getHistogramData(@Validated SummaryQuery query) {
        return monitorService.requestHistogramData(query);
    }

    @GetMapping("/summary")
    @ApiOperation(
            value = "采集数据汇总表格数据接口",
            notes = "采集数据汇总表格数据接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSummaryData(@Validated SummaryQuery query) {
        return monitorService.requestSummaryData(query);
    }

    @GetMapping("/node/histogram")
    @ApiOperation(
            value = "环节类型数据竖形柱状图信息数据接口",
            notes = "环节类型数据竖形柱状图信息数据接口，需要登录验证，" +
                    "提供日期范围返回指定时间段的数据，否则返回前15天的数据"
    )
    public Result getMonitorHistogramData(@Validated SummaryQuery query) {
        return monitorService.requestMonitorHistogramData(query);
    }

    @GetMapping("/node/pie")
    @ApiOperation(
            value = "环节类型数据饼图信息数据接口",
            notes = "环节类型数据饼图信息数据接口，需要登录验证，" +
                    "提供日期范围返回指定时间段的数据，否则返回前15天的数据"
    )
    public Result getMonitorPieData(@Validated SummaryQuery query) {
        return monitorService.requestMonitorPieData(query);
    }

    @GetMapping("/node")
    @ApiOperation(
            value = "环节类型数据信息接口",
            notes = "环节类型数据信息接口，需要登录验证，" +
                    "不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getMonitorData(@Validated SummaryQuery query) {
        return monitorService.requestMonitorData(query);
    }

    @GetMapping("/info/pie")
    @ApiOperation(
            value = "查看明细饼图数据接口",
            notes = "查看明细饼图数据接口，需要登录验证，" +
                    "提供日期范围返回指定时间段的数据"
    )
    public Result getMonitorInfoPie(@Validated InfoQuery query) {
        return monitorService.requestMonitorInfoPie(query);
    }

    @GetMapping("/info")
    @ApiOperation(
            value = "查看明细表格数据接口",
            notes = "查看明细表格数据接口，需要登录验证，" +
                    "不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getMonitorInfoTable(@Validated InfoQuery query) {
        return monitorService.requestMonitorInfo(query);
    }

}
