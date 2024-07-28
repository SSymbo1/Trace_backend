package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 通讯消息枚举
public enum Message {

    EDIT_ENTERPRISE_FAIL("编辑企业信息失败"),
    EDIT_ENTERPRISE_SUCCESS("编辑企业信息成功"),
    GET_ENTERPRISE_EDIT_DATA("获取指定企业信息成功"),
    GET_ENTERPRISE_OPERATE_SUCCESS("查询企业敏感操作记录成功!"),
    ADD_ENTERPRISE_SUCCESS("创建企业成功!"),
    ADD_ENTERPRISE_FAIL("创建企业失败!"),
    ADD_ENTERPRISE_EXIST("所添加的企业已存在!"),
    GET_ENTERPRISE_SUCCESS("查询公司信息成功!"),
    GET_ACCOUNT_OPERATE_SUCCESS("查询账户敏感操作记录成功!"),
    EDIT_ACCOUNT_SUCCESS("修改账户成功!"),
    EDIT_ACCOUNT_FAIL("修改账户失败!"),
    GET_ACCOUNT_EDIT_DATA("获取指定账户信息成功"),
    SET_ACCOUNT_STATUE_SUCCESS("设置账户状态成功"),
    SET_ACCOUNT_STATUE_FAIL("设置账户状态失败"),
    ADD_ACCOUNT_SUCCESS("添加账户成功!"),
    ADD_ACCOUNT_FAIL("添加账户失败!"),
    ACCOUNT_USER_EXIST("所注册的用户名存在!"),
    DECODE_WRONG("AES解码失败!"),
    DECODED("已解码!"),
    NO_PERMISSION("没有足够的权限!"),
    TIMESTAMP_TIMEOUT("解码请求超时!"),
    GET_ENTERPRISE_SUB_SUCCESS("查询公司子菜单成功"),
    GET_ROLE_SUB_SUCCESS("查询角色子菜单成功!"),
    GET_LOGIN_ACCOUNT_INFO_SUCCESS("查询当前登录用户信息成功!"),
    GET_ACCOUNT_INFO_SUCCESS("查询用户信息成功"),
    SAVE_SUCCESS("保存成功"),
    SAVE_ERROR("保存失败"),
    GET_MONITOR_MENUE_SUCCESS("获取运行监测管理菜单成功!"),
    GET_SEGMENT_MENUE_SUCCESS("获取追溯环节管理菜单成功!"),
    GET_ANALYSIS_MENUE_SUCCESS("获取追溯数据分析菜单成功!"),
    GET_SUBJECT_MENUE_SUCCESS("获取追溯主体管理菜单成功!"),
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
