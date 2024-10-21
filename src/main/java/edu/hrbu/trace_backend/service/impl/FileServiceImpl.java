package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.ObjectId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.enums.Folder;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.enums.Statue;
import edu.hrbu.trace_backend.entity.excel.ProductRecord;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.service.FileService;
import edu.hrbu.trace_backend.util.ExcelUtil;
import edu.hrbu.trace_backend.util.FileUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private ClassificationMapper classificationMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductRecordMapper productRecordMapper;
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;

    @Override
    public Result requestAvatarUpload(MultipartFile file) {
        Result result = FileUtil.saveFile(file, Folder.AVATAR.getValue());
        if (!Statue.SUCCESS.getValue().equals(result.getCode())) {
            return Result.fail(Message.SAVE_ERROR.getValue());
        }
        return Result
                .ok(Message.SAVE_SUCCESS.getValue())
                .data("avatar", result.getData());
    }

    @Override
    public Result requestPhotoUpload(MultipartFile file, Integer goodsId) {
        Result result = FileUtil.saveFile(file, Folder.GOODS.getValue());
        if (!Statue.SUCCESS.getValue().equals(result.getCode())) {
            return Result.fail(Message.SAVE_ERROR.getValue());
        }
        Product product = Product.builder()
                .pid(goodsId)
                .photo((String) result.getData().get("name")).build();
        productMapper.updateById(product);
        return Result
                .ok(Message.SAVE_SUCCESS.getValue())
                .data("photo", result.getData());
    }

    @Override
    @SneakyThrows
    public Result requestProductAddByExcel(MultipartFile file) {
        DateTime localTime = new DateTime(DateTime.now());
        List<ProductRecord> excelData = ExcelUtil.importExcel(file.getInputStream(), ProductRecord.class);
        excelData.forEach(data -> {
            QueryWrapper<AccountInfo> accountNameWrapper = new QueryWrapper<>();
            QueryWrapper<Enterprise> enterpriseIdWrapper = new QueryWrapper<>();
            QueryWrapper<Classification> classificationIdWrapper = new QueryWrapper<>();
            accountNameWrapper.eq("name", data.getOperator());
            enterpriseIdWrapper
                    .eq("name", data.getEnterprise())
                    .and(id -> id.eq("social_code", data.getSocialCode()));
            classificationIdWrapper.eq("name", data.getClassification());
            Product insertProduct = Product.builder()
                    .cid(classificationMapper.selectOne(classificationIdWrapper).getCid())
                    .eid(enterpriseMapper.selectOne(enterpriseIdWrapper).getEid())
                    .name(data.getName())
                    .photo("default.png")
                    .code(data.getCode())
                    .unit(data.getUnit())
                    .isMajor(data.getIsMajor().equals("是") ? 1 : 0)
                    .build();
            productMapper.insert(insertProduct);
            edu.hrbu.trace_backend.entity.po.ProductRecord insertProductRecord = edu.hrbu.trace_backend.entity.po.ProductRecord.builder()
                    .pid(insertProduct.getPid())
                    .aid(accountInfoMapper.selectOne(accountNameWrapper).getAid())
                    .num(data.getNum())
                    .processTime("")
                    .insertTime(localTime.toString("yyyy-MM-dd HH:mm:ss"))
                    .importType(0).build();
            productRecordMapper.insert(insertProductRecord);
        });
        return Result.ok(Message.BATCH_IMPORT_PRODUCT.getValue());
    }

    @Override
    @SneakyThrows
    public Result requestApproachByExcel(MultipartFile file) {
        List<edu.hrbu.trace_backend.entity.excel.Approach> approaches = ExcelUtil.importExcel(
                file.getInputStream(),
                edu.hrbu.trace_backend.entity.excel.Approach.class
        );
        for (edu.hrbu.trace_backend.entity.excel.Approach approach : approaches) {
            DateTime operateTime = new DateTime(DateTime.now());
            Approach insertApproach = Approach.builder()
                    .eid(enterpriseMapper.selectOne(
                            new QueryWrapper<Enterprise>()
                                    .eq("name", approach.getBusinessName())
                                    .and(code -> code.eq("social_code", approach.getBusinessCode()))).getEid()
                    )
                    .name(approach.getName())
                    .code(approach.getCode())
                    .num(approach.getNum())
                    .unit(approach.getUnit())
                    .batch(approach.getBatch())
                    .trace(approach.getTraceCode())
                    .cid(classificationMapper.selectOne(
                            new QueryWrapper<Classification>()
                                    .eq("name", approach.getClassName())
                    ).getCid())
                    .businessTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
            approachMapper.insert(insertApproach);
        }
        return Result.ok(Message.APPROACH_SUCCESS.getValue());
    }

    @Override
    @SneakyThrows
    public Result requestEntranceByExcel(MultipartFile file) {
        List<edu.hrbu.trace_backend.entity.excel.Entrance> entrances = ExcelUtil.importExcel(
                file.getInputStream(),
                edu.hrbu.trace_backend.entity.excel.Entrance.class
        );
        for (edu.hrbu.trace_backend.entity.excel.Entrance entrance : entrances) {
            DateTime operateTime = new DateTime(DateTime.now());
            Entrance insertEntrance = null;
            if (entrance.getTraceCode() == null || entrance.getTraceCode().isEmpty()) {
                insertEntrance = Entrance.builder()
                        .bid(enterpriseMapper.selectOne(
                                new QueryWrapper<Enterprise>()
                                        .eq("name", entrance.getBusinessName())
                                        .and(code -> code.eq("social_code", entrance.getBusinessCode()))).getEid()
                        )
                        .name(entrance.getName())
                        .code(entrance.getCode())
                        .num(entrance.getNum())
                        .unit(entrance.getUnit())
                        .batch(entrance.getBatch())
                        .cid(classificationMapper.selectOne(
                                new QueryWrapper<Classification>()
                                        .eq("name", entrance.getClassName())
                        ).getCid())
                        .buyerType(entrance.getBuyerType().equals("个人") ? 0 : 1)
                        .trace("Trace-" + ObjectId.next())
                        .businessTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
            } else {
                insertEntrance = Entrance.builder()
                        .bid(enterpriseMapper.selectOne(
                                new QueryWrapper<Enterprise>()
                                        .eq("name", entrance.getBusinessName())
                                        .and(code -> code.eq("social_code", entrance.getBusinessCode()))).getEid()
                        )
                        .name(entrance.getName())
                        .code(entrance.getCode())
                        .num(entrance.getNum())
                        .unit(entrance.getUnit())
                        .batch(entrance.getBatch())
                        .cid(classificationMapper.selectOne(
                                new QueryWrapper<Classification>()
                                        .eq("name", entrance.getClassName())
                        ).getCid())
                        .buyerType(entrance.getBuyerType().equals("个人") ? 0 : 1)
                        .trace(entrance.getTraceCode())
                        .businessTime(operateTime.toString("yyyy-MM-dd HH:mm:ss")).build();
            }
            entranceMapper.insert(insertEntrance);
        }
        return Result.ok(Message.ENTRANCE_SUCCESS.getValue());
    }
}
