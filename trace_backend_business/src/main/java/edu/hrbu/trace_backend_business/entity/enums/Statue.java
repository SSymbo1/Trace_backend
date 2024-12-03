package edu.hrbu.trace_backend_business.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 状态码枚举
public enum Statue {

    SUCCESS(200),
    FREQUENCY(300),
    FAIL(500),
    TABLE_WRONG(510),
    WRONG_ARGUMENT(450),
    WRONG_AES(430),
    WRONG_TOKEN(420),
    EXPIRE_TOKEN(410);

    private final Integer value;

}
