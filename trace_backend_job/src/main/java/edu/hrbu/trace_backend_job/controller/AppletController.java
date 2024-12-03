package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.annotation.TrafficLimit;
import edu.hrbu.trace_backend_business.service.AppletService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "微信小程序数据接口")
@RequestMapping("/wechat")
public class AppletController {

    @Resource
    private AppletService appletService;

    @TrafficLimit
    @GetMapping("/trace")
    @ApiOperation(
            value = "获取追溯产品数据接口",
            notes = "获取追溯产品数据接口，提供产品追溯码，返回商品追溯数据"
    )
    public Result getTraceProductInfo(String traceCode) {
        return appletService.requestTraceProductInfo(traceCode);
    }

}
