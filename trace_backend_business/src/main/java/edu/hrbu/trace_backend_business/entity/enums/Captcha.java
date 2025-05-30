package edu.hrbu.trace_backend_business.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 图形验证码属性枚举
public enum Captcha {

    WIDTH("width",100),
    HEIGHT("height",40),
    CODE_COUNT("count",4),
    THICKNESS("扭曲",5),
    CIRCLE_COUNT("圆形",10);

    private final String key;
    private final Integer value;

}
