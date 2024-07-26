package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 操作枚举
public enum Operate {

    ACCOUNT_EDIT("修改账号信息"),
    ACCOUNT_ADD_FAIL("创建账号失败"),
    ACCOUNT_ADD("创建账号"),
    ACCOUNT_STATUE_SET("修改账号状态"),
    DECODE("请求解码密码");

    private final String value;

}
