package edu.hrbu.trace_backend_job.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ExcelUtil {

    public static void exportExcel(File file, Class<?> clazz, List<?> data) {
        EasyExcel.write(file, clazz)
                .sheet("sheet")
                .doWrite(data);
    }

    public static <T> List<T> importExcel(InputStream inputStream, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        EasyExcel.read(inputStream, clazz, new AnalysisEventListener<T>() {
            @Override
            public void invoke(T data, AnalysisContext analysisContext) {
                list.add(data);
            }
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
        }).sheet("sheet").doRead();
        return list;
    }

}
