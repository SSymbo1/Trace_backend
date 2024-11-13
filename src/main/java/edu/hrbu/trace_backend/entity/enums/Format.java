package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
//  通用格式化枚举
public enum Format {

    FULL_TIME_FORMAT("yyyy-MM-dd HH:mm:ss"),
    FULL_DATE_FORMAT("yyyy-MM-dd"),
    YEAR_MONTH_FORMAT("yyyy-MM"),
    ONLY_YEAR_FORMAT("yyyy");

    private final String value;
}
