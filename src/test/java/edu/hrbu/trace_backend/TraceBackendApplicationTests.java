package edu.hrbu.trace_backend;

import edu.hrbu.trace_backend.entity.po.StructReport;
import edu.hrbu.trace_backend.mapper.StructReportMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


@Slf4j
@SpringBootTest
class TraceBackendApplicationTests {

    @Resource
    StructReportMapper structReportMapper;

    @Test
    public void test() {
        StructReport structReport = structReportMapper.selectById(1);
        log.info(structReport.toString());
    }

}
