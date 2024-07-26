package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 状态码枚举
public enum Statue {

    SUCCESS(200),
    FAIL(500),
    WRONG_AES(403),
    WRONG_TOKEN(402),
    EXPIRE_TOKEN(401);

    private final Integer value;

}
