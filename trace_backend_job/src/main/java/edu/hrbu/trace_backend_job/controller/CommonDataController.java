package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.annotation.AntiResubmit;
import edu.hrbu.trace_backend_business.annotation.TrafficLimit;
import edu.hrbu.trace_backend_business.entity.dto.Decode;
import edu.hrbu.trace_backend_business.service.CommonDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "通用数据获取接口")
@RequestMapping("/common")
public class CommonDataController {

    @Resource
    private CommonDataService commonDataService;

    @GetMapping("/statistics/card")
    @ApiOperation(
            value = "首页数据统计卡片数据接口",
            notes = "首页数据统计卡片数据接口，需要登录验证，" +
                    "返回首页:账户统计，追溯总数居，流通节点企业总数，重要产品卡片渲染时需要的数据"
    )
    public Result getHomeStatisticsCardData() {
        return commonDataService.requestHomeStatisticsCardData();
    }

    @GetMapping("/statistics/line")
    @ApiOperation(
            value = "首页数据统计柱状图数据接口",
            notes = "首页数据统计柱状图数据接口，需要登录验证，" +
                    "返回首页数据采集柱状图渲染所需要的数据"
    )
    public Result getHomeStatisticsLineData() {
        return commonDataService.requestHomeStatisticsLineData();
    }

    @GetMapping("/platform/collect/ring")
    @ApiOperation(
            value = "可视化平台获取数据采集环图数据",
            notes = "返回可视化平台数据采集环形数据"
    )
    public Result getPlatformDataCollectRingData() {
        return commonDataService.requestPlatformDataCollectData();
    }

    @GetMapping("/platform/enterprise")
    @ApiOperation(
            value = "可视化平台获取企业数据接口",
            notes = "返回可视化平台企业数据"
    )
    public Result getPlatformEnterpriseData() {
        return commonDataService.requestPlatformEnterpriseData();
    }

    @GetMapping("/platform/product")
    @ApiOperation(
            value = "可视化平台获取产品备案数据接口",
            notes = "返回可视化平台产品备案数据"
    )
    public Result getPlatformProductData() {
        return commonDataService.requestPlatformProductData();
    }

    @GetMapping("/platform/trace")
    @ApiOperation(
            value = "可视化平台获取追溯数据接口",
            notes = "返回可视化平台追溯数据"
    )
    public Result getPlatformTraceData() {
        return commonDataService.requestPlatformTraceData();
    }

    @GetMapping("/platform/map")
    @ApiOperation(
            value = "可视化平台获取地图数据接口",
            notes = "返回可视化平台地图数据"
    )
    public Result getPlatformMapData() {
        return commonDataService.requestPlatformMap();
    }

    @TrafficLimit
    @GetMapping("/whois")
    @ApiOperation(
            value = "获取当前用户信息接口",
            notes = "当用户登录成功时，前端从该接口可以获取用户的详细信息，" +
                    "需要登陆验证"
    )
    public Result getLoginUserInfo() {
        return commonDataService.requestWhoIs();
    }

    @AntiResubmit
    @PostMapping("/decode/password")
    @ApiOperation(
            value = "解码加密后密码接口",
            notes = "解码加密后密码接口，前端从该接口可以获取提供密码的解码，" +
                    "需要登陆验证以及角色验证"
    )
    public Result decodePassword(@RequestBody @Validated Decode decode) {
        return commonDataService.requestDecodePass(decode);
    }

    @TrafficLimit
    @GetMapping("/account")
    @ApiOperation(
            value = "获取用户修改信息接口",
            notes = "获取用户修改信息接口，需要登陆验证，" +
                    "在需要修改账户信息时，请求该接口获取修改前数据"
    )
    public Result getEditAccountInfo(Integer accountId) {
        return commonDataService.requestEditAccountInfo(accountId);
    }

    @TrafficLimit
    @GetMapping("/enterprise")
    @ApiOperation(
            value = "获取企业修改信息接口",
            notes = "获取企业修改信息接口，需要登陆验证，" +
                    "在需要修改企业信息时，请求该接口获取修改前数据"
    )
    public Result getEditEnterpriseInfo(Integer enterpriseId) {
        return commonDataService.requestEditEnterpriseInfo(enterpriseId);
    }

    @TrafficLimit
    @GetMapping("/role")
    @ApiOperation(
            value = "获取角色修改信息接口",
            notes = "获取角色修改信息接口，需要登陆验证，" +
                    "在需要修改角色信息时，请求该接口获取修改前数据"
    )
    public Result getEditRoleInfo(Integer roleId) {
        return commonDataService.requestEditRoleInfo(roleId);
    }

    @TrafficLimit
    @GetMapping("/product")
    @ApiOperation(
            value = "获取产品修改信息接口",
            notes = "获取产品修改信息接口，需要登陆验证，" +
                    "在需要修改产品信息时，请求该接口获取修改前数据"
    )
    public Result getEditProductInfo(Integer productId) {
        return commonDataService.requestProductInfo(productId);
    }

    @TrafficLimit
    @GetMapping("/approver")
    @ApiOperation(
            value = "获取审批人员信息接口",
            notes = "获取审批人员信息接口，需要登陆验证，" +
                    "在查看产品详细信息时，请求该接口获取修改前数据"
    )
    public Result getApproverName(Integer approverId) {
        return commonDataService.requestApproveInfo(approverId);
    }

}
