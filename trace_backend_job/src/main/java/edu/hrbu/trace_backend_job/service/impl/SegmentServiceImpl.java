package edu.hrbu.trace_backend_job.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.segment.ApproachQuery;
import edu.hrbu.trace_backend_job.entity.dto.segment.EntranceQuery;
import edu.hrbu.trace_backend_job.entity.enums.Message;
import edu.hrbu.trace_backend_job.entity.po.Approach;
import edu.hrbu.trace_backend_job.entity.po.Entrance;
import edu.hrbu.trace_backend_job.mapper.ApproachMapper;
import edu.hrbu.trace_backend_job.mapper.EntranceMapper;
import edu.hrbu.trace_backend_job.service.SegmentService;
import edu.hrbu.trace_backend_job.util.QRCodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SegmentServiceImpl implements SegmentService {

    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;

    @Override
    @SneakyThrows
    public Result requestTraceQrCode(String trace) {
        BufferedImage qrCode = (BufferedImage) QRCodeUtil.generateQRCode(trace).getData().get("img");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(qrCode, "png", byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        return Result
                .ok(Message.GET_TRACE_QR_SUCCESS.getValue())
                .data("qr", Base64.getEncoder().encodeToString(imageBytes));
    }

    @Override
    public Result requestApproachInfoPaged(ApproachQuery query) {
        IPage<Approach> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getApproachCondition(query);
        return Result
                .ok(Message.GET_APPROACH_SUCCESS.getValue())
                .data("iPage", approachMapper.selectApproachInfoByCondition(page, condition));
    }

    @Override
    public Result requestEntranceInfoPaged(EntranceQuery query) {
        IPage<Entrance> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getEntranceCondition(query);
        return Result
                .ok(Message.GET_ENTRANCE_SUCCESS.getValue())
                .data("iPage", entranceMapper.selectEntranceInfoByCondition(page, condition));
    }

    private Map<String, Object> getApproachCondition(ApproachQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("batch", query.getBatch());
        condition.put("trace", query.getTrace());
        return condition;
    }

    private Map<String, Object> getEntranceCondition(EntranceQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("batch", query.getBatch());
        condition.put("trace", query.getTrace());
        condition.put("buyerType", query.getBuyerType());
        return condition;
    }

}
