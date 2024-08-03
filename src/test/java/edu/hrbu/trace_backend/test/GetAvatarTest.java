package edu.hrbu.trace_backend.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.cron.service.TrashDataClearService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
public class GetAvatarTest {

    @Resource
    private TrashDataClearService trashDataClearService;

    @Test
    public void getAvatarLocalTest(){
        trashDataClearService.removeExpireAccountOperate();
    }

}
