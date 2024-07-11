package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.vo.Welcome;
import edu.hrbu.trace_backend.service.WelcomeService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "敏感数据操作接口")
@RequestMapping("/welcome")
public class WelcomeController {

    @Resource
    private WelcomeService welcomeService;

    @PostMapping("/login")
    private Result login(@RequestBody Welcome welcome) {
        return welcomeService.login(welcome);
    }

    @GetMapping("/picture_captcha")
    private Result getPictureCaptcha() {
        return welcomeService.pictureCaptcha();
    }
}
