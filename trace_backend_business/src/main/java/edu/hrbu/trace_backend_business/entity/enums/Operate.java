package edu.hrbu.trace_backend_business.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 操作枚举
public enum Operate {

    ENABLE_ALL_ROLE("启用所有角色"),
    DISABLE_ALL_ROLE("禁用所有角色"),
    ROLE_EDIT("修改角色信息"),
    ROLE_STATUE_SET("修改角色状态"),
    ROLE_ADD("创建角色"),
    ROLE_ADD_FAIL("创建角色失败"),
    ENABLE_ALL_ACCOUNT("启用所有账户"),
    DISABLE_ALL_ACCOUNT("禁用所有账户"),
    ENTERPRISE_EDIT("修改企业信息"),
    ENTERPRISE_ADD("创建企业"),
    ENTERPRISE_ADD_FAIL("创建企业失败"),
    ACCOUNT_EDIT("修改账号信息"),
    ACCOUNT_ADD_FAIL("创建账号失败"),
    ACCOUNT_ADD("创建账号"),
    ACCOUNT_STATUE_SET("修改账号状态"),
    DECODE("请求解码密码");

    private final String value;

}
