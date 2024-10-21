package edu.hrbu.trace_backend;

import edu.hrbu.trace_backend.entity.enums.Folder;
import edu.hrbu.trace_backend.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@MapperScan("edu.hrbu.trace_backend.mapper")
@SpringBootApplication
public class TraceBackendApplication {

    public static void main(String[] args) {
        //  初始化资源文件夹
        initApplicationResourcesFolder();
        //  启动应用
        SpringApplication.run(TraceBackendApplication.class, args);
    }

    public static void initApplicationResourcesFolder(){
        log.info("执行初始化资源文件夹操作");
        FileUtil.createFolder(Folder.AVATAR.getValue());
        FileUtil.createFolder(Folder.GOODS.getValue());
        FileUtil.createFolder(Folder.LOG.getValue());
    }

}
