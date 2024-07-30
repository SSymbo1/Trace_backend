package edu.hrbu.trace_backend.service.impl;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Folder;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.enums.Statue;
import edu.hrbu.trace_backend.service.FileService;
import edu.hrbu.trace_backend.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public Result avatarUpload(MultipartFile file) {
        Result result = FileUtil.saveFile(file, Folder.AVATAR.getValue());
        if (!Statue.SUCCESS.getValue().equals(result.getCode())) {
            return Result.fail(Message.SAVE_ERROR.getValue());
        }
        return Result
                .ok(Message.SAVE_SUCCESS.getValue())
                .data("avatar",result.getData());
    }
}
