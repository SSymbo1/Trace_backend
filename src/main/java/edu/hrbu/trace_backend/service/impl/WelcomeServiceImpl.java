package edu.hrbu.trace_backend.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ShearCaptcha;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.RePassword;
import edu.hrbu.trace_backend.entity.po.Account;
import edu.hrbu.trace_backend.entity.enums.Captcha;
import edu.hrbu.trace_backend.entity.enums.Condition;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.dto.PictureCaptcha;
import edu.hrbu.trace_backend.entity.dto.Welcome;
import edu.hrbu.trace_backend.mapper.AccountMapper;
import edu.hrbu.trace_backend.service.WelcomeService;
import edu.hrbu.trace_backend.util.AesUtil;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        if (accountMapper.selectList(queryWrapper).isEmpty()) {
            return Result.fail(Message.ABSENT_USER.getValue());
        }
        queryWrapper.and(
                condition -> condition.eq("password", AesUtil.encryptHex(welcome.getPassword()))
        );
        Account userStatue = accountMapper.selectOne(queryWrapper);
        if (userStatue == null) {
            return Result.fail(Message.WRONG_USERNAME_OR_PASSWORD.getValue());
        }
        if (userStatue.getBan().equals(Condition.ACCOUNT_DISABLED.getValue())) {
            return Result.fail(Message.ACCOUNT_DISABLE.getValue());
        }
        if (userStatue.getDel().equals(Condition.ACCOUNT_DELETED.getValue())) {
            return Result.fail(Message.ACCOUNT_DELETE.getValue());
        }
        return Result
                .ok(Message.LOGIN_SUCCESS.getValue() + userStatue.getUsername())
                .data("token", JwtUtil.createJWT(String.valueOf(userStatue.getAid())));
    }

    @Override
    public Result rePassword(RePassword rePassword) {
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        if (!rePassword.getVerify().equalsIgnoreCase(rePassword.getCaptcha())) {
            return Result.fail(Message.WRONG_CAPTCHA.getValue());
        }
        long currentTimeStamp = System.currentTimeMillis();
        if (currentTimeStamp - rePassword.getTimestamp() > 60000) {
            return Result.fail(Message.TIMESTAMP_TIMEOUT.getValue());
        }
        Account account = Account.builder()
                .aid(currentAccountId)
                .password(AesUtil.encryptHex(rePassword.getPassword())).build();
        accountMapper.updateById(account);
        return Result.ok(Message.RE_PASSWORD_SUCCESS.getValue());
    }

    @Override
    public Result pictureCaptcha() {
        Random random = new Random();
        PictureCaptcha pictureCaptcha = PictureCaptcha.builder().build();
        Integer captchaType = random.nextInt(2);
        if (captchaType.equals(Condition.CIRCLE_CAPTCHA.getValue())) {
            CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(
                    Captcha.WIDTH.getValue(),
                    Captcha.HEIGHT.getValue(),
                    Captcha.CODE_COUNT.getValue(),
                    Captcha.CIRCLE_COUNT.getValue()
            );
            pictureCaptcha.setPicture(captcha.getImageBase64());
            pictureCaptcha.setCaptcha(captcha.getCode());
        }
        if (captchaType.equals(Condition.SHEAR_CAPTCHA.getValue())) {
            ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(
                    Captcha.WIDTH.getValue(),
                    Captcha.HEIGHT.getValue(),
                    Captcha.CODE_COUNT.getValue(),
                    Captcha.THICKNESS.getValue()
            );
            pictureCaptcha.setPicture(captcha.getImageBase64());
            pictureCaptcha.setCaptcha(captcha.getCode());
        }
        log.info(
                "生成{}验证码:{}",
                captchaType.equals(Condition.CIRCLE_CAPTCHA.getValue()) ?
                        Captcha.CIRCLE_COUNT.getKey() : Captcha.THICKNESS.getKey(),
                pictureCaptcha.getCaptcha()
        );
        return Result
                .ok(Message.GET_CAPTCHA_SUCCESS.getValue())
                .data("captcha", pictureCaptcha);
    }

}
