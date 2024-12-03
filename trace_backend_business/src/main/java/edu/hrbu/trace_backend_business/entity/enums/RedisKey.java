package edu.hrbu.trace_backend_business.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
//  Redis键枚举
public enum RedisKey {

    RENEWAL("renewal:"),
    USER("user:"),
    TRAFFIC("traffic:");

    private final String value;
}
