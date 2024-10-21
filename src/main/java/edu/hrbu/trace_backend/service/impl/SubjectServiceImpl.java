package edu.hrbu.trace_backend.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend.entity.OnlineContext;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.subject.Product;
import edu.hrbu.trace_backend.entity.dto.subject.ProductQuery;
import edu.hrbu.trace_backend.entity.dto.subject.SupplierQuery;
import edu.hrbu.trace_backend.entity.dto.subject.VendorsQuery;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.ProductRecord;
import edu.hrbu.trace_backend.entity.po.Supplier;
import edu.hrbu.trace_backend.mapper.ProductMapper;
import edu.hrbu.trace_backend.mapper.ProductRecordMapper;
import edu.hrbu.trace_backend.mapper.SupplierMapper;
import edu.hrbu.trace_backend.service.SubjectService;
import edu.hrbu.trace_backend.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private ProductMapper productMapper;
    @Resource
    private ProductRecordMapper productRecordMapper;
    @Value("${resources.goods}")
    private String photoPath;

    @Override
    public Result requestSupplierPaged(SupplierQuery query) {
        IPage<Supplier> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getSupplierSearchCondition(query);
        return Result
                .ok(Message.GET_SUPPLIER_SUCCESS.getValue())
                .data("iPage", supplierMapper.selectSupplierByCondition(page, condition));
    }

    @Override
    public Result requestVendorsPaged(VendorsQuery query) {
        IPage<Supplier> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getVendorsSearchCondition(query);
        return Result
                .ok(Message.GET_VENDORS_SUCCESS.getValue())
                .data("iPage", supplierMapper.selectVendorsByCondition(page, condition));
    }

    @Override
    public Result requestProductPaged(ProductQuery query) {
        IPage<edu.hrbu.trace_backend.entity.po.Product> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getProductSearchCondition(query);
        IPage<edu.hrbu.trace_backend.entity.po.Product> productPage = productMapper.selectProductByCondition(
                page, condition
        );
        productPage.getRecords().forEach(product -> product.setPhoto(photoPath + product.getPhoto()));
        return Result
                .ok(Message.GET_PRODUCT_SUCCESS.getValue())
                .data("iPage", productPage);
    }

    @Override
    public Result requestProductAdd(Product product) {
        DateTime localTime = new DateTime(DateTime.now());
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        edu.hrbu.trace_backend.entity.po.Product insertProduct = edu.hrbu.trace_backend.entity.po.Product.builder()
                .cid(product.getCid())
                .eid(product.getEnterprise())
                .name(product.getName())
                .code(product.getCode())
                .unit(product.getUnit())
                .isMajor(product.getIsMajor())
                .photo("default.png").build();
        int insertProductStatue = productMapper.insert(insertProduct);
        ProductRecord insertProductRecord = ProductRecord.builder()
                .pid(insertProduct.getPid())
                .aid(currentAccountId)
                .num(product.getNum())
                .processTime("")
                .insertTime(localTime.toString("yyyy-MM-dd HH:mm:ss"))
                .importType(1).build();
        int insertRecordStatue = productRecordMapper.insert(insertProductRecord);
        return insertRecordStatue > 0 && insertProductStatue > 0 ?
                Result.ok(Message.ADD_PRODUCT_SUCCESS.getValue()) :
                Result.fail(Message.ADD_PRODUCT_FAIL.getValue());
    }

    @Override
    public Result requestEditProduct(Product product) {
        DateTime localTime = new DateTime(DateTime.now());
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        edu.hrbu.trace_backend.entity.po.Product updateProduct = edu.hrbu.trace_backend.entity.po.Product.builder()
                .pid(product.getPid())
                .cid(product.getCid())
                .eid(product.getEnterprise())
                .name(product.getName())
                .code(product.getCode())
                .unit(product.getUnit())
                .isMajor(product.getIsMajor())
                .build();
        ProductRecord updateProductRecord = ProductRecord.builder()
                .aid(currentAccountId)
                .num(product.getNum())
                .insertTime(localTime.toString("yyyy-MM-dd HH:mm:ss"))
                .build();
        int updateProductStatue = productMapper.updateById(updateProduct);
        int updateStatue = productRecordMapper
                .update(updateProductRecord, new QueryWrapper<ProductRecord>().eq("pid", product.getPid()));
        return updateProductStatue > 0 && updateStatue > 0 ?
                Result.ok(Message.EDIT_PRODUCT_SUCCESS.getValue()) :
                Result.fail(Message.EDIT_PRODUCT_FAIL.getValue());
    }

    @Override
    public Result requestProductRecordPaged(ProductQuery query) {
        IPage<edu.hrbu.trace_backend.entity.po.Product> page = new Page<>(query.getCurrentPage(), query.getPageSize());
        Map<String, Object> condition = getProductSearchCondition(query);
        IPage<edu.hrbu.trace_backend.entity.po.Product> productPage = productRecordMapper.selectProductRecordProcessByCondition(
                page, condition
        );
        productPage.getRecords().forEach(product -> product.setPhoto(photoPath + product.getPhoto()));
        return Result
                .ok(Message.GET_PRODUCT_SUCCESS.getValue())
                .data("iPage", productPage);
    }

    @Override
    public Result requestProcessProductRecordBatched(Product[] products) {
        DateTime localTime = new DateTime(DateTime.now());
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        for (Product product : products) {
            ProductRecord updateProductRecord = ProductRecord.builder()
                    .approver(currentAccountId)
                    .statue(product.getStatue())
                    .processTime(localTime.toString("yyyy-MM-dd HH:mm:ss")).build();
            productRecordMapper.update(
                    updateProductRecord,
                    new QueryWrapper<ProductRecord>().eq("pid", product.getPid())
            );
        }
        return Result.ok(Message.PROCESS_APPROVE.getValue());
    }

    @Override
    public Result requestApproveProductRecord(Product product) {
        DateTime localTime = new DateTime(DateTime.now());
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        ProductRecord updateProductRecord = ProductRecord.builder()
                .approver(currentAccountId)
                .statue(1)
                .processTime(localTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        int updateStatue = productRecordMapper.update(
                updateProductRecord,
                new QueryWrapper<ProductRecord>().eq("pid", product.getPid())
        );
        return updateStatue > 0 ?
                Result.ok(Message.PROCESS_APPROVE.getValue()) :
                Result.fail(Message.PROCESS_REJECT.getValue());
    }

    @Override
    public Result requestRejectProductRecord(Product product) {
        DateTime localTime = new DateTime(DateTime.now());
        Integer currentAccountId = Integer.valueOf(JwtUtil.parseJWT(OnlineContext.getCurrent()).getSubject());
        ProductRecord updateProductRecord = ProductRecord.builder()
                .approver(currentAccountId)
                .statue(2)
                .processTime(localTime.toString("yyyy-MM-dd HH:mm:ss")).build();
        int updateStatue = productRecordMapper.update(
                updateProductRecord,
                new QueryWrapper<ProductRecord>().eq("pid", product.getPid())
        );
        return updateStatue > 0 ?
                Result.ok(Message.PROCESS_APPROVE.getValue()) :
                Result.fail(Message.PROCESS_REJECT.getValue());
    }

    private Map<String, Object> getSupplierSearchCondition(SupplierQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("code", query.getCode());
        condition.put("generate", query.getGenerate());
        condition.put("is_trace", query.getIsTrace());
        return condition;
    }

    private Map<String, Object> getVendorsSearchCondition(VendorsQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("code", query.getCode());
        condition.put("generate", query.getGenerate());
        condition.put("is_trace", query.getIsTrace());
        return condition;
    }

    private Map<String, Object> getProductSearchCondition(ProductQuery query) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("name", query.getName());
        condition.put("enterprise", query.getEnterprise());
        condition.put("classification", query.getClassification());
        condition.put("isMajor", query.getIsMajor());
        condition.put("importType", query.getImportType());
        condition.put("statue", query.getStatue());
        return condition;
    }


}
