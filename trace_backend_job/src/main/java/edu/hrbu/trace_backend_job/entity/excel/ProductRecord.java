package edu.hrbu.trace_backend_job.entity.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//  产品备案excel
public class ProductRecord {

    @ColumnWidth(10)
    @ExcelProperty(value = "编号", index = 0)
    private long id;

    @ColumnWidth(25)
    @ExcelProperty(value = "企业名称", index = 1)
    private String enterprise;

    @ColumnWidth(20)
    @ExcelProperty(value = "企业编码", index = 2)
    private String socialCode;

    @ColumnWidth(25)
    @ExcelProperty(value = "产品名称", index = 3)
    private String name;

    @ColumnWidth(20)
    @ExcelProperty(value = "产品编码", index = 4)
    private String code;

    @ColumnWidth(15)
    @ExcelProperty(value = "产品分类", index = 5)
    private String classification;

    @ColumnWidth(10)
    @ExcelProperty(value = "单位", index = 6)
    private String unit;

    @ColumnWidth(10)
    @ExcelProperty(value = "数量", index = 7)
    private Integer num;

    @ColumnWidth(20)
    @ExcelProperty(value = "是否重要产品", index = 8)
    private String isMajor;

    @ColumnWidth(10)
    @ExcelProperty(value = "操作者", index = 9)
    private String operator;

}
