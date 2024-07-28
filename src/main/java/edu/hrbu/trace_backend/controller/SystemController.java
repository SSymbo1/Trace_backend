package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.*;
import edu.hrbu.trace_backend.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "系统设置管理数据接口")
@RequestMapping("/system")
public class SystemController {

    @Resource
    private SystemService systemService;

    @GetMapping("/account")
    @ApiOperation(
            value = "查询分页用户信息接口",
            notes = "查询分页用户信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getAccountInfoPaged(UserQuery query) {
        return systemService.requestAccountInfoPaged(query);
    }

    @PostMapping("/account")
    @ApiOperation(
            value = "添加用户信息接口",
            notes = "添加用户信息接口，需要登录验证，" +
                    "提供新增用户所需要的信息，返回执行结果"
    )
    public Result addAccountInfo(@RequestBody Account account) {
        return systemService.requestAccountAdd(account);
    }

    @PutMapping("/account")
    @ApiOperation(
            value = "用户信息编辑接口",
            notes = "用户信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改账号的详细信息，返回修改结果"
    )
    public Result editAccountInfo(@RequestBody Account account) {
        return systemService.requestAccountEdit(account);
    }

    @PutMapping("/account/statue")
    @ApiOperation(
            value = "用户状态修改接口",
            notes = "用户状态修改接口，需要登录验证，" +
                    "根据提供的数据修改账号的状态，例如封禁和删除"
    )
    public Result accountStatueSet(@RequestBody AccountStatue statue) {
        return systemService.requestAccountStatueSet(statue);
    }

    @GetMapping("/sensitive/account")
    @ApiOperation(
            value = "查询分页账户敏感操作记录接口",
            notes = "查询分页账户敏感操作记录接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSensitiveAccountInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        return systemService.requestSensitiveAccountInfoPaged(keyword, currentPage, pageSize);
    }

    @GetMapping("/sensitive/enterprise")
    @ApiOperation(
            value = "查询分页企业敏感操作记录接口",
            notes = "查询分页企业敏感操作记录接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSensitiveEnterpriseInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        return systemService.requestSensitiveEnterpriseInfoPaged(keyword, currentPage, pageSize);
    }

    @GetMapping("/enterprise")
    @ApiOperation(
            value = "查询分页企业信息接口",
            notes = "查询分页企业信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getEnterpriseInfoPaged(EnterpriseQuery query) {
        return systemService.requestEnterpriseInfoPaged(query);
    }

    @PostMapping("/enterprise")
    @ApiOperation(
            value = "添加企业信息接口",
            notes = "添加企业信息接口，需要登录验证，" +
                    "提供新增企业所需要的信息，返回执行结果"
    )
    public Result addEnterprise(@RequestBody Enterprise enterprise) {
        return systemService.requestEnterpriseAdd(enterprise);
    }

    @PutMapping("/enterprise")
    @ApiOperation(
            value = "企业信息编辑接口",
            notes = "企业信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改企业的详细信息，返回修改结果"
    )
    public Result editEnterprise(@RequestBody Enterprise enterprise) {
        return systemService.requestEnterpriseEdit(enterprise);
    }

    @DeleteMapping("/enterprise")
    @ApiOperation(
            value = "企业信息删除接口",
            notes = "企业信息删除接口，需要登录验证，" +
                    "根据提供的数据删除企业信息，返回修改结果"
    )
    public Result deleteEnterprise(@RequestBody Enterprise enterprise) {
        return null;
    }


}
