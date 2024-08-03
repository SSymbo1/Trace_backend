package edu.hrbu.trace_backend.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.subject.Product;
import edu.hrbu.trace_backend.entity.dto.subject.ProductQuery;
import edu.hrbu.trace_backend.entity.dto.subject.SupplierQuery;
import edu.hrbu.trace_backend.entity.dto.subject.VendorsQuery;
import edu.hrbu.trace_backend.entity.enums.Message;
import edu.hrbu.trace_backend.entity.po.Supplier;
import edu.hrbu.trace_backend.mapper.ProductMapper;
import edu.hrbu.trace_backend.mapper.ProductRecordMapper;
import edu.hrbu.trace_backend.mapper.SupplierMapper;
import edu.hrbu.trace_backend.service.SubjectService;
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
        IPage<edu.hrbu.trace_backend.entity.po.Product> productPage = productMapper.selectProductByCondition(page, condition);
        productPage.getRecords().forEach(product -> product.setPhoto(photoPath + product.getPhoto()));
        return Result
                .ok(Message.GET_PRODUCT_SUCCESS.getValue())
                .data("iPage", productPage);
    }

    @Override
    public Result requestProductAdd(Product product) {
        return null;
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
        condition.put("statue",query.getStatue());
        return condition;
    }


}
