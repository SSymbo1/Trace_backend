package edu.hrbu.trace_backend.function;

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
public class XlsxTest {

    @Test
    public void xlsxTest(){
        ExcelUtil.exportExcel(new File("D:/桌面/超市进场表.xlsx"), Approach.class,null);
        ExcelUtil.exportExcel(new File("D:/桌面/超市出场表.xlsx"), Entrance.class,null);
    }

}
