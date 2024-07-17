package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 条件枚举
public enum Condition {

    CIRCLE_CAPTCHA(0),
    SHEAR_CAPTCHA(1),
    ACCOUNT_DISABLED(1),
    ACCOUNT_DELETED(1);

    private final Integer value;

}
