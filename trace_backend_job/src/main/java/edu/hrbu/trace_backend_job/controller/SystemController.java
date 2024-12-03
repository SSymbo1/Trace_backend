package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.annotation.AntiResubmit;
import edu.hrbu.trace_backend_business.annotation.TrafficLimit;
import edu.hrbu.trace_backend_business.entity.dto.system.*;
import edu.hrbu.trace_backend_business.service.SystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
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

    @TrafficLimit
    @GetMapping("/account")
    @ApiOperation(
            value = "查询分页用户信息接口",
            notes = "查询分页用户信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getAccountInfoPaged(@Validated UserQuery query) {
        return systemService.requestAccountInfoPaged(query);
    }

    @AntiResubmit
    @PostMapping("/account")
    @ApiOperation(
            value = "添加用户信息接口",
            notes = "添加用户信息接口，需要登录验证，" +
                    "提供新增用户所需要的信息，返回执行结果"
    )
    public Result addAccountInfo(@RequestBody Account account) {
        return systemService.requestAccountAdd(account);
    }

    @AntiResubmit
    @PutMapping("/account")
    @ApiOperation(
            value = "用户信息编辑接口",
            notes = "用户信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改账号的详细信息，返回修改结果"
    )
    public Result editAccountInfo(@RequestBody Account account) {
        return systemService.requestAccountEdit(account);
    }

    @AntiResubmit
    @PutMapping("/account/statue")
    @ApiOperation(
            value = "用户状态修改接口",
            notes = "用户状态修改接口，需要登录验证，" +
                    "根据提供的数据修改账号的状态，例如封禁和删除"
    )
    public Result accountStatueSet(@RequestBody AccountStatue statue) {
        return systemService.requestAccountStatueSet(statue);
    }

    @AntiResubmit
    @PutMapping("/account/enable")
    @ApiOperation(
            value = "启用所有用户接口",
            notes = "启用所有用户接口，需要登录验证，" +
                    "根据提供的数据验证后启用所有账户"
    )
    public Result enableAllAccount(@RequestBody Able able) {
        return systemService.requestEnableAllAccount(able);
    }

    @AntiResubmit
    @PutMapping("/account/disable")
    @ApiOperation(
            value = "禁用所有用户接口",
            notes = "禁用所有用户接口，需要登录验证，" +
                    "根据提供的数据验证后禁用所有账户"
    )
    public Result disableAllAccount(@RequestBody Able able) {
        return systemService.requestDisableAllAccount(able);
    }

    @TrafficLimit
    @GetMapping("/enterprise")
    @ApiOperation(
            value = "查询分页企业信息接口",
            notes = "查询分页企业信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getEnterpriseInfoPaged(@Validated EnterpriseQuery query) {
        return systemService.requestEnterpriseInfoPaged(query);
    }

    @AntiResubmit
    @PostMapping("/enterprise")
    @ApiOperation(
            value = "添加企业信息接口",
            notes = "添加企业信息接口，需要登录验证，" +
                    "提供新增企业所需要的信息，返回执行结果"
    )
    public Result addEnterprise(@RequestBody Enterprise enterprise) {
        return systemService.requestEnterpriseAdd(enterprise);
    }

    @AntiResubmit
    @PutMapping("/enterprise")
    @ApiOperation(
            value = "企业信息编辑接口",
            notes = "企业信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改企业的详细信息，返回修改结果"
    )
    public Result editEnterprise(@RequestBody Enterprise enterprise) {
        return systemService.requestEnterpriseEdit(enterprise);
    }

    @TrafficLimit
    @GetMapping("/role")
    @ApiOperation(
            value = "查询分页角色信息接口",
            notes = "查询分页角色信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getRoleInfoPaged(@Validated RoleQuery query) {
        return systemService.requestRoleInfoPaged(query);
    }

    @AntiResubmit
    @PostMapping("/role")
    @ApiOperation(
            value = "添加角色信息接口",
            notes = "添加角色信息接口，需要登录验证，" +
                    "提供新增角色所需要的信息，返回执行结果 "
    )
    public Result addRole(@RequestBody Role role) {
        return systemService.requestRoleAdd(role);
    }

    @AntiResubmit
    @PutMapping("/role")
    @ApiOperation(
            value = "角色信息编辑接口",
            notes = "角色信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改角色的详细信息，返回修改结果"
    )
    public Result editRole(@RequestBody Role role) {
        return systemService.requestRoleEdit(role);
    }

    @AntiResubmit
    @PutMapping("/role/statue")
    @ApiOperation(
            value = "角色状态修改接口",
            notes = "角色状态修改接口，需要登录验证，" +
                    "根据提供的数据修改角色的状态，例如禁用和删除"
    )
    public Result roleStatueSet(@RequestBody RoleStatue statue) {
        return systemService.requestRoleStatueSet(statue);
    }

    @AntiResubmit
    @PutMapping("/role/enable")
    @ApiOperation(
            value = "启用所有角色接口",
            notes = "启用所有角色接口，需要登录验证，" +
                    "根据提供的数据验证后启用所有角色"
    )
    public Result enableAllRole(@RequestBody Able able) {
        return systemService.requestEnableAllRole(able);
    }

    @AntiResubmit
    @PutMapping("/role/disable")
    @ApiOperation(
            value = "禁用所有角色接口",
            notes = "禁用所有角色接口，需要登录验证，" +
                    "根据提供的数据验证后禁用所有角色"
    )
    public Result disableAllRole(@RequestBody Able able) {
        return systemService.requestDisableAllRole(able);
    }

    @TrafficLimit
    @GetMapping("/sensitive/account")
    @ApiOperation(
            value = "查询分页账户敏感操作记录接口",
            notes = "查询分页账户敏感操作记录接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSensitiveAccountInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        return systemService.requestSensitiveAccountInfoPaged(keyword, currentPage, pageSize);
    }

    @TrafficLimit
    @GetMapping("/sensitive/enterprise")
    @ApiOperation(
            value = "查询分页企业敏感操作记录接口",
            notes = "查询分页企业敏感操作记录接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSensitiveEnterpriseInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        return systemService.requestSensitiveEnterpriseInfoPaged(keyword, currentPage, pageSize);
    }

    @TrafficLimit
    @GetMapping("/sensitive/role")
    @ApiOperation(
            value = "查询分页角色敏感操作记录接口",
            notes = "查询分页角色敏感操作记录接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSensitiveRoleInfoPaged(String keyword, Integer currentPage, Integer pageSize) {
        return systemService.requestSensitiveRoleInfoPaged(keyword, currentPage, pageSize);
    }

}
