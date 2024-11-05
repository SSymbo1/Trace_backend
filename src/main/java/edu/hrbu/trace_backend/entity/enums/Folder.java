package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 资源文件夹枚举
public enum Folder {

    WINDOWS("D:/trace/"),
    LINUX("/media/vdc/trace/"),
    AVATAR("avatar"),
    GOODS("goods"),
    LOG("log");

    private final String value;
}
