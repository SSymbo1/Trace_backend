package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.UserQuery;
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

    @GetMapping("/get_account_info")
    @ApiOperation(
            value = "查询分页用户信息接口",
            notes = "查询分页用户信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getAccountInfoPaged(UserQuery query){
        return systemService.requestAccountInfoPaged(query);
    }

}
