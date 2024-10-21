package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 企业类型枚举
public enum EnterpriseType {

    UNIT("团体单位",0),
    PLANT("种植企业",1),
    ANIMAL("养殖企业",2),
    PRODUCT("生产加工",3),
    BUTCH("屠宰企业",4),
    BATCH("批发市场",5),
    FARM("农贸市场",6),
    SHOP("连锁超市",7);

    private final String key;
    private final Integer value;

}
