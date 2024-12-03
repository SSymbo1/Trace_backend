package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.annotation.TrafficLimit;
import edu.hrbu.trace_backend_business.entity.dto.segment.ApproachQuery;
import edu.hrbu.trace_backend_business.entity.dto.segment.EntranceQuery;
import edu.hrbu.trace_backend_business.service.SegmentService;
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
@Api(tags = "追溯环节管理数据接口")
@RequestMapping("/segment")
public class SegmentController {

    @Resource
    private SegmentService segmentService;

    @TrafficLimit
    @GetMapping("/QRCode")
    @ApiOperation(
            value = "追溯二维码请求接口",
            notes = "追溯二维码请求接口，需要登录验证" +
                    "需要提供追溯码，返回二维码"
    )
    public Result getTraceQrCode(String trace) {
        return segmentService.requestTraceQrCode(trace);
    }

    @TrafficLimit
    @GetMapping("/entrance")
    @ApiOperation(
            value = "查询超市出场分页数据接口",
            notes = "查询超市出场分页数据接口，需要登录验证," +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getEntranceInfoPaged(@Validated EntranceQuery query) {
        return segmentService.requestEntranceInfoPaged(query);
    }

    @TrafficLimit
    @GetMapping("/approach")
    @ApiOperation(
            value = "查询超市进场分页数据接口",
            notes = "查询超市进场分页数据接口，需要登录验证," +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getApproachInfoPaged(@Validated ApproachQuery query) {
        return segmentService.requestApproachInfoPaged(query);
    }

}
