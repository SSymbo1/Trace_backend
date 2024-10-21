package edu.hrbu.trace_backend.xlsx;

import edu.hrbu.trace_backend.entity.excel.Approach;
import edu.hrbu.trace_backend.entity.excel.Entrance;
import edu.hrbu.trace_backend.entity.excel.ProductRecord;
import edu.hrbu.trace_backend.util.ExcelUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@Slf4j
@SpringBootTest
public class XLSXTest {

    /***
     * 测试生成超市进场，出场模板表格
     */
    @Test
    public void generateXlsxModelTest() {
        ExcelUtil.exportExcel(new File("D:/桌面/超市进场表.xlsx"), Approach.class, null);
        log.info("生成进场模板表格");
        ExcelUtil.exportExcel(new File("D:/桌面/超市出场表.xlsx"), Entrance.class, null);
        log.info("生成出场模板表格");
        ExcelUtil.exportExcel(new File("D:/桌面/重要产品备案申请表.xlsx"), ProductRecord.class, null);
        log.info("生成备案申请表");
    }

}
