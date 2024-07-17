package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Welcome;
import edu.hrbu.trace_backend.service.WelcomeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "用户感知接口")
@RequestMapping("/welcome")
public class WelcomeController {

    @Resource
    private WelcomeService welcomeService;

    @PostMapping("/login")
    @ApiOperation(
            value = "用户登录接口",
            notes = "用户登录接口，请求这个接口时需要携带用户名，" +
                    "密码，验证码，登录成功返回token，失败返回错误信息"
    )
    private Result login(@RequestBody Welcome welcome) {
        return welcomeService.login(welcome);
    }

    @GetMapping("/picture_captcha")
    @ApiOperation(
            value = "图片验证码请求接口",
            notes = "图片验证码请求接口，请求接口时返回图片验证码的base64编码和验证码的值"
    )
    private Result getPictureCaptcha() {
        return welcomeService.pictureCaptcha();
    }
}
