package edu.hrbu.trace_backend_job.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
// 中国省份枚举
public enum Province {

    BEIJING("北京市", 0),
    TIANJIN("天津市", 0),
    HEBEI("河北省", 0),
    SHANXI("山西省", 0),
    NEIMENGGU("内蒙古自治区", 0),
    LIAONING("辽宁省", 0),
    JILIN("吉林省", 0),
    HEILONGJIANG("黑龙江省", 0),
    SHANGHAI("上海市", 0),
    JIANGSU("江苏省", 0),
    ZHEJIANG("浙江省", 0),
    ANHUI("安徽省", 0),
    FUJIAN("福建省", 0),
    JIANGXI("江西省", 0),
    SHANDONG("山东省", 0),
    HENAN("河南省", 0),
    HUBEI("湖北省", 0),
    HUNAN("湖南省", 0),
    GUANGDONG("广东省", 0),
    GUANGXI("广西壮族自治区", 0),
    HAINAN("海南省", 0),
    CHONGQING("重庆市", 0),
    SICHUAN("四川省", 0),
    GUIZHOU("贵州省", 0),
    YUNNAN("云南省", 0),
    XIZANG("西藏自治区", 0),
    SHAANXI("陕西省", 0),
    GANSU("甘肃省", 0),
    QINGHAI("青海省", 0),
    NINGXIA("宁夏回族自治区", 0),
    XINJIANG("新疆维吾尔自治区", 0),
    HONGKONG("香港特别行政区", 0),
    MACAU("澳门特别行政区", 0),
    TAIWAN("台湾省", 0);

    private final String key;
    private final Integer value;

}
