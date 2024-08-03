package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.service.MonitorService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
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

}
