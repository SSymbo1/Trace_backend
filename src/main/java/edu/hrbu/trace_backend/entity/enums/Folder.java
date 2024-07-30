package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Folder {

    WINDOWS("D:/trace/"),
    LINUX("/media/vdc/trace/"),
    AVATAR("avatar");
    private final String value;
}
