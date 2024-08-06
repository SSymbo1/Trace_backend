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

    @PostMapping("/upload/avatar")
    @ApiOperation(
            value = "用户头像上传接口",
            notes = "用户头像上传接口，需要登录验证，" +
                    "上传用户头像，上传成功返回图片的url(图片全名)"
    )
    public Result uploadAvatar(@RequestBody MultipartFile[] avatar) {
        return fileService.requestAvatarUpload(avatar[0]);
    }

    @PostMapping("/upload/goods")
    @ApiOperation(
            value = "产品图片上传接口",
            notes = "产品图片上传接口，需要登录验证，" +
                    "上传产品图片，上传成功返回图片的url(图片全名)"
    )
    public Result uploadGoodsPhoto(@RequestBody MultipartFile[] photo,Integer goodsId) {
        return fileService.requestPhotoUpload(photo[0],goodsId);
    }

    @PostMapping("/upload/product")
    @ApiOperation(
            value = "产品备案表格导入接口",
            notes = "产品备案表格导入接口，需要登录验证" +
                    "上传产品备案表格，校验表格是否合法"
    )
    public Result addProductByExcel(@RequestBody MultipartFile[] excel) {
        return fileService.requestProductAddByExcel(excel[0]);
    }

    @PostMapping("/upload/approach")
    @ApiOperation(
            value = "超市进场表格导入接口",
            notes = "超市进场表格导入接口，需要登录验证" +
                    "上传超市进场备案表格，校验表格是否合法"
    )
    public Result addApproachByExcel(@RequestBody MultipartFile[] excel){
        return fileService.requestApproachByExcel(excel[0]);
    }

    @PostMapping("/upload/entrance")
    @ApiOperation(
            value = "超市出场表格导入接口",
            notes = "超市出场表格导入接口，需要登录验证" +
                    "上传超市出场备案表格，校验表格是否合法"
    )
    public Result addEntranceByExcel(@RequestBody MultipartFile[] excel){
        return fileService.requestEntranceByExcel(excel[0]);
    }

}
