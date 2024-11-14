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
//  超市进场excel
public class Approach {

    @ColumnWidth(10)
    @ExcelProperty(value = "编号", index = 0)
    private long id;

    @ColumnWidth(20)
    @ExcelProperty(value = "经营商户代码", index = 1)
    private String businessCode;

    @ColumnWidth(25)
    @ExcelProperty(value = "经营商户名称", index = 2)
    private String businessName;

    @ColumnWidth(25)
    @ExcelProperty(value = "产品代码", index = 3)
    private String code;

    @ColumnWidth(25)
    @ExcelProperty(value = "产品名称", index = 4)
    private String name;

    @ColumnWidth(10)
    @ExcelProperty(value = "批次号", index = 5)
    private String batch;

    @ColumnWidth(25)
    @ExcelProperty(value = "追溯码", index = 6)
    private String traceCode;

    @ColumnWidth(10)
    @ExcelProperty(value = "数量", index = 7)
    private Integer num;

    @ColumnWidth(10)
    @ExcelProperty(value = "单位", index = 8)
    private String unit;

    @ColumnWidth(15)
    @ExcelProperty(value = "产品分类", index = 9)
    private String className;

}
