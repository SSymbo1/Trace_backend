package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "文件io接口")
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("/upload_avatar")
    @ApiOperation(
            value = "用户头像上传接口",
            notes = "用户头像上传接口，需要登录验证，" +
                    "上传用户头像，上传成功返回图片的url(图片全名)"
    )
    public Result uploadAvatar(@RequestBody MultipartFile[] avatar){
        return fileService.avatarUpload(avatar[0]);
    }

}
