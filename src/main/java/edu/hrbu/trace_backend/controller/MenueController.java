package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.service.MenueService;
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
@Api(tags = "菜单接口")
@RequestMapping("/menue")
public class MenueController {

    @Resource
    private MenueService menueService;

    @GetMapping("/home")
    @ApiOperation(
            value = "首页功能菜单请求接口",
            notes = "首页功能菜单请求接口，需要登录验证，" +
                    "返回首页渲染功能菜单所需要的菜单数据"
    )
    public Result getHomeMenue() {
        return menueService.requestHomeMenue();
    }

    @GetMapping("/subject")
    @ApiOperation(
            value = "追溯主体管理菜单请求接口",
            notes = "追溯主体管理菜单请求接口，需要登录验证，" +
                    "返回追溯主体管理菜单请求接口渲染功能菜单所需要的菜单数据"
    )
    public Result getSubjectMenue(){
        return menueService.requestSubjectMenue();
    }

    @GetMapping("/analysis")
    @ApiOperation(
            value = "追溯数据分析菜单请求接口",
            notes = "追溯数据分析菜单请求接口，需要登录验证，" +
                    "返回追溯数据管理菜单请求接口渲染功能菜单所需要的菜单数据"
    )
    public Result getAnalysisMenue(){
        return menueService.requestAnalysisMenue();
    }

    @GetMapping("/monitor")
    @ApiOperation(
            value = "运行监测管理菜单请求接口",
            notes = "运行监测管理菜单请求接口，需要登录验证，" +
                    "返回运行监测管理菜单请求接口渲染功能菜单所需要的菜单数据"
    )
    public Result getMonitorMenue(){
        return menueService.requestMonitorMenue();
    }

    @GetMapping("/segment")
    @ApiOperation(
            value = "追溯环节管理菜单请求接口",
            notes = "追溯环节管理菜单请求接口，需要登录验证，" +
                    "返回运行追溯环节菜单请求接口渲染功能菜单所需要的菜单数据"
    )
    public Result getSegmentMenue(){
        return menueService.requestSegmentMenue();
    }

    @GetMapping("/system")
    @ApiOperation(
            value = "系统设置管理菜单请求接口",
            notes = "系统设置管理菜单请求接口，需要登录验证，" +
                    "返回运行系统设置管理菜单请求接口渲染功能菜单所需要的菜单数据"
    )
    public Result getSystemMenue(){
        return menueService.requestSystemMenue();
    }

    @GetMapping("/sub/role")
    @ApiOperation(
            value = "角色菜单数据接口",
            notes = "角色菜单数据接口，需要登录验证，" +
                    "返回角色选择菜单，包括rid和name值"
    )
    public Result getAccountRoleMenue(){
        return menueService.requestRoleSubMenue();
    }

    @GetMapping("/sub/enterprise")
    @ApiOperation(
            value = "公司菜单数据接口",
            notes = "公司菜单数据接口，需要登录验证，" +
                    "返回角色选择菜单，包括eid和name值"
    )
    public Result getAccountEnterpriseMenue(String keyword){
        return menueService.requestEnterpriseMenue(keyword);
    }

}
