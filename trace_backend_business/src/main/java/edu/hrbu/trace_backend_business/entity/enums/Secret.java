package edu.hrbu.trace_backend_business.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 密钥盐枚举
public enum Secret {

    JWT("aghebndkfcvw#DECPLz0123456789"),
    AES("sxbagtlSXBAGTL318957%@^&" ),
    RENEWAL_LOCK("1"),
    JWT_RENEWAL("600000"),
    JWT_EXPIRE("3600000");

    private final String value;

}
