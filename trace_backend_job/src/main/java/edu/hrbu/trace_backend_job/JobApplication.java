package edu.hrbu.trace_backend_job;

import edu.hrbu.trace_backend_job.entity.enums.Folder;
import edu.hrbu.trace_backend_job.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@MapperScan("edu.hrbu.trace_backend_job.mapper")
@SpringBootApplication
@EnableTransactionManagement
public class JobApplication {

    public static void main(String[] args) {
        initApplicationResourcesFolder();
        SpringApplication.run(JobApplication.class, args);
    }

    public static void initApplicationResourcesFolder() {
        log.info("执行初始化资源文件夹操作");
        FileUtil.createFolder(Folder.AVATAR.getValue());
        FileUtil.createFolder(Folder.GOODS.getValue());
        FileUtil.createFolder(Folder.LOG.getValue());
    }

}
