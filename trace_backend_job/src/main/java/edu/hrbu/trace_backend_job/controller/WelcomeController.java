package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.RePassword;
import edu.hrbu.trace_backend_job.entity.dto.Welcome;
import edu.hrbu.trace_backend_job.service.WelcomeService;
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
    public Result login(@RequestBody Welcome welcome) {
        return welcomeService.login(welcome);
    }

    @PutMapping("/password")
    @ApiOperation(
            value = "用户修改密码接口",
            notes = "用户修改密码接口，调用这个接口时将会记录日志，" +
                    "需要提供修改后的密码"
    )
    public Result rePassword(@RequestBody RePassword rePassword){
        return welcomeService.rePassword(rePassword);
    }

    @GetMapping("/captcha")
    @ApiOperation(
            value = "图片验证码请求接口",
            notes = "图片验证码请求接口，请求接口时返回图片验证码的base64编码和验证码的值"
    )
    public Result getPictureCaptcha() {
        return welcomeService.pictureCaptcha();
    }
}
