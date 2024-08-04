package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Result requestAvatarUpload(MultipartFile file);

    Result requestPhotoUpload(MultipartFile file,Integer goodsId);

    Result requestProductAddByExcel(MultipartFile file);

}
