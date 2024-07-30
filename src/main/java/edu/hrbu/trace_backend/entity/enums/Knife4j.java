package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// Knife4j
public enum Knife4j {

    TITLE("重要产品溯源管理系统-项目接口文档"),
    AUTHOR("SSymbol"),
    GITHUB("https://gitee.com/SS_ymbol"),
    EMAIL("ss_ymbol@qq.com"),
    VERSION("dev"),
    DESCRIPTION("这是重要产品溯源管理系统项目接口文档"),
    GROUP("开发版本");

    private final String value;

}
