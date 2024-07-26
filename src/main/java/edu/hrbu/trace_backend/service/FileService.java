package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Transactional
public interface FileService {

    Result avatarUpload(MultipartFile file);

}
