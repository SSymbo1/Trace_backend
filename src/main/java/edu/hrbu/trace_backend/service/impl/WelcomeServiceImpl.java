package edu.hrbu.trace_backend.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Account;
import edu.hrbu.trace_backend.entity.vo.PictureCaptcha;
import edu.hrbu.trace_backend.entity.vo.Welcome;
import edu.hrbu.trace_backend.mapper.AccountMapper;
import edu.hrbu.trace_backend.service.WelcomeService;
import edu.hrbu.trace_backend.util.AesUtil;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class WelcomeServiceImpl implements WelcomeService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Result login(Welcome welcome) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", welcome.getUsername());
        if (!welcome.getVerify().equalsIgnoreCase(welcome.getCaptcha())) {
            return Result.fail("验证码错误!");
        }
        if (accountMapper.selectList(queryWrapper).isEmpty()) {
            return Result.fail("用户不存在!");
        }
        queryWrapper.and(condition -> condition.eq("password", AesUtil.encryptHex(welcome.getPassword())));
        List<Account> userStatue = accountMapper.selectList(queryWrapper);
        if (userStatue.isEmpty()) {
            return Result.fail("用户名或密码错误!");
        }
        if (userStatue.get(0).getBan() == 1) {
            return Result.fail("该账号已被暂停使用，详情请联系管理员!");
        }
        if (userStatue.get(0).getDel() == 1) {
            return Result.fail("该账号已被删除，详情请联系管理员!");
        }
        return Result.ok("登录成功，欢迎:"+userStatue.get(0).getUsername()).data("token", JwtUtil.createJWT(String.valueOf(userStatue.get(0).getAid())));
    }

    @Override
    public Result pictureCaptcha() {
        Random random = new Random();
        PictureCaptcha pictureCaptcha = PictureCaptcha.builder().build();
        int captchaType = random.nextInt(2);
        if (captchaType == 0) {
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(100, 40, 4, 10);
            pictureCaptcha.setPicture(captcha.getImageBase64());
            pictureCaptcha.setCaptcha(captcha.getCode());
        } else {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 40, 4, 5);
            pictureCaptcha.setPicture(captcha.getImageBase64());
            pictureCaptcha.setCaptcha(captcha.getCode());
        }
        log.info("生成{}验证码:{}", captchaType == 0 ? "圆形" : "扭曲", pictureCaptcha.getCaptcha());
        return Result.ok("获取图像验证码成功").data("captcha", pictureCaptcha);
    }
}
