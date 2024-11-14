package edu.hrbu.trace_backend_job.service;

import edu.hrbu.trace_backend_job.entity.Result;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Result requestAvatarUpload(MultipartFile file);

    Result requestPhotoUpload(MultipartFile file,Integer goodsId);

    Result requestProductAddByExcel(MultipartFile file);

    Result requestApproachByExcel(MultipartFile file);

    Result requestEntranceByExcel(MultipartFile file);

}
