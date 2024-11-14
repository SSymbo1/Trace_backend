package edu.hrbu.trace_backend_job.util;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.enums.Folder;
import edu.hrbu.trace_backend_job.entity.enums.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Component
public class FileUtil {

    public static String getSystemPath() {
        String os = System.getProperty("os.name");
        String url = "";
        if (os.toLowerCase().startsWith("win")) {
            url = Folder.WINDOWS.getValue();
        } else {
            url = Folder.LINUX.getValue();
        }
        return url;
    }

    public static Result saveFile(MultipartFile file, String path) {
        createFolderIfNotExist(path);
        String fileName = setFileUUID(file);
        try {
            byte[] bytes = file.getBytes();
            Files.write(Paths.get(getSystemPath() + path + "/" + fileName), bytes);
        } catch (IOException exception) {
            log.info("保存文件失败");
            return Result.fail(Message.SAVE_ERROR.getValue());
        }
        log.info("保存上传文件成功，地址:{}{}/{}", getSystemPath(), path, fileName);
        return Result.ok(Message.SAVE_SUCCESS.getValue()).data("name", fileName);
    }

    public static void createFolder(String folder) {
        if (!cn.hutool.core.io.FileUtil.isDirectory(getSystemPath() + folder)) {
            cn.hutool.core.io.FileUtil.mkdir(getSystemPath() + folder);
            log.info("已初始化资源文件夹:{}", folder);
        } else {
            log.info("已存在资源文件夹:{}", folder);
        }
    }

    private static void createFolderIfNotExist(String folder) {
        File root = new File(getSystemPath() + folder);
        if (!root.exists()) {
            root.mkdirs();
            File background = new File(folder);
            if (!background.exists()) {
                background.mkdirs();
            }
        }
    }

    private static String setFileUUID(MultipartFile file) {
        return UUID.randomUUID() + "_" + file.getOriginalFilename();
    }

}
