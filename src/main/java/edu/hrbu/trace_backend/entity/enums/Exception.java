package edu.hrbu.trace_backend.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
//  异常信息枚举
public enum Exception {

    EXCEL_NAME_TOO_LONG("名称过长，请控制在25个字符以内!"),
    EXCEL_CODE_TOO_LONG("编码过长，请控制在25个字符以内!"),
    EXCEL_LINE_NULL("上传的电子表格存在空值,请检查后重新提交!"),
    EXCEL_OPERATOR("操作者未在系统注册，无法导入!"),
    EXCEL_CLASS("产品分类未在系统注册，无法导入!"),
    EXCEL_ENTERPRISE_REGISTERED("企业未在系统注册，无法导入!"),
    EXCEL_STRUCT_HEAD_NUM("产品备案表头数量不匹配，无法导入!"),
    EXCEL_STRUCT_HEAD("产品备案表头不匹配，缺失的表头有: "),
    EXCEL_NULL("产品备案表无数据，无法导入!");

    private final String value;
}
