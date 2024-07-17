package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 通讯消息枚举
public enum Message {

    SERVER_ERROR("出现未知错误!"),
    LOGIN_ERROR("登录失效，请重新登录!"),
    EXPIRE_TOKEN("token已过期!"),
    WRONG_TOKEN("不合法的token!"),
    GET_HOME_MENUE_SUCCESS("获取主界面菜单成功!"),
    GET_CAPTCHA_SUCCESS("获取验证码成功!"),
    WRONG_CAPTCHA("验证码错误!"),
    ABSENT_USER("用户不存在!"),
    WRONG_USERNAME_OR_PASSWORD("用户名或密码错误!"),
    ACCOUNT_DISABLE("该账号已被暂停使用，详情请联系管理员!"),
    ACCOUNT_DELETE("该账号已被删除，详情请联系管理员!"),
    LOGIN_SUCCESS("登录成功，欢迎:");

    private final String value;

}
