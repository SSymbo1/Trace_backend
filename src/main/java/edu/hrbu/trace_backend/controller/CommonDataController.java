package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.service.CommonDataService;
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
@Api(tags = "通用数据获取接口")
@RequestMapping("/common")
public class CommonDataController {

    @Resource
    private CommonDataService commonDataService;

    // todo 首页数据统计卡片的数据接口
    @GetMapping("/home_statistics_card")
    @ApiOperation(
            value = "首页数据统计卡片数据接口",
            notes = "首页数据统计卡片数据接口，需要登录验证，" +
                    "返回首页:账户统计，追溯总数居，流通节点企业总数，重要产品卡片渲染时需要的数据"
    )
    public Result getHomeStatisticsCardData() {
        return commonDataService.requestHomeStatisticsCardData();
    }

    // todo 首页数据统计柱状图的数据接口
    @GetMapping("/home_statistics_line")
    @ApiOperation(
            value = "首页数据统计柱状图数据接口",
            notes = "首页数据统计柱状图数据接口，需要登录验证，" +
                    "返回首页数据采集柱状图渲染所需要的数据"
    )
    public Result getHomeStatisticsLineData() {
        return commonDataService.requestHomeStatisticsLineData();
    }

}
