package edu.hrbu.trace_backend_job.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 表映射枚举
public enum Table {

    MENUE_SUBJECT(1, "追溯主体管理"),
    MENUE_ANALYSIS(4, "追溯数据分析"),
    MENUE_MONITOR(7, "运行监测管理"),
    MENUE_SEGMENT(8, "追溯环节管理"),
    MENUE_SYSTEM(10, "系统设置管理");

    private final Integer value;
    private final String key;
}
