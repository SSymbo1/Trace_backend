package edu.hrbu.trace_backend_job.service.impl;

import com.alibaba.excel.EasyExcel;
import edu.hrbu.trace_backend_job.entity.excel.Approach;
import edu.hrbu.trace_backend_job.entity.excel.Entrance;
import edu.hrbu.trace_backend_job.entity.excel.ProductRecord;
import edu.hrbu.trace_backend_job.global.checker.ApproachExcelChecker;
import edu.hrbu.trace_backend_job.global.checker.EntranceExcelChecker;
import edu.hrbu.trace_backend_job.global.exception.excel.ExcelTypeException;
import edu.hrbu.trace_backend_job.global.checker.ProductExcelChecker;
import edu.hrbu.trace_backend_job.service.ExcelCheckService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Objects;

@Slf4j
@Service
public class ExcelCheckServiceImpl implements ExcelCheckService {

    @Override
    @SneakyThrows
    public void requestProductExcelCheck(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MultipartFile[] file = (MultipartFile[]) args[0];
        if (!Objects.equals(
                file[0].getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        ) {
            throw new ExcelTypeException("上传的电子表格格式不正确,应为.xlsx格式");
        }
        InputStream inputStream = file[0].getInputStream();
        EasyExcel.read(inputStream, ProductRecord.class, new ProductExcelChecker())
                .sheet("sheet")
                .doRead();
    }

    @Override
    @SneakyThrows
    public void requestApproachExcelCheck(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MultipartFile[] file = (MultipartFile[]) args[0];
        if (!Objects.equals(
                file[0].getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        ) {
            throw new ExcelTypeException("上传的电子表格格式不正确,应为.xlsx格式");
        }
        InputStream inputStream = file[0].getInputStream();
        EasyExcel.read(inputStream, Approach.class, new ApproachExcelChecker())
                .sheet("sheet")
                .doRead();
    }

    @Override
    @SneakyThrows
    public void requestEntranceExcelCheck(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MultipartFile[] file = (MultipartFile[]) args[0];
        if (!Objects.equals(
                file[0].getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        ) {
            throw new ExcelTypeException("上传的电子表格格式不正确,应为.xlsx格式");
        }
        InputStream inputStream = file[0].getInputStream();
        EasyExcel.read(inputStream, Entrance.class, new EntranceExcelChecker())
                .sheet("sheet")
                .doRead();
    }
}
