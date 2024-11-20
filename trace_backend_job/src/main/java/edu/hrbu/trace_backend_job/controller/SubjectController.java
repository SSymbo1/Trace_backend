package edu.hrbu.trace_backend_job.controller;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.subject.Product;
import edu.hrbu.trace_backend_business.entity.dto.subject.ProductQuery;
import edu.hrbu.trace_backend_business.entity.dto.subject.SupplierQuery;
import edu.hrbu.trace_backend_business.entity.dto.subject.VendorsQuery;
import edu.hrbu.trace_backend_business.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@CrossOrigin
@RestController
@Api(tags = "追溯主体管理数据接口")
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private SubjectService subjectService;

    @GetMapping("/supplier")
    @ApiOperation(
            value = "查询分页供应商信息接口",
            notes = "查询分页供应商信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getSupplierPaged(@Validated SupplierQuery query) {
        return subjectService.requestSupplierPaged(query);
    }

    @GetMapping("/vendors")
    @ApiOperation(
            value = "查询分页供销商信息接口",
            notes = "查询分页供销商信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getVendorsPaged(@Validated VendorsQuery query) {
        return subjectService.requestVendorsPaged(query);
    }

    @GetMapping("/product")
    @ApiOperation(
            value = "查询产品分页信息接口",
            notes = "查询产品分页信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getProductPaged(@Validated ProductQuery query) {
        return subjectService.requestProductPaged(query);
    }

    @PostMapping("/product")
    @ApiOperation(
            value = "添加产品备案接口",
            notes = "添加产品备案接口，需要登录验证，" +
                    "提供产品备案所需要的信息，返回执行结果"
    )
    public Result addProductRecord(@RequestBody @Validated Product product) {
        return subjectService.requestProductAdd(product);
    }

    @PutMapping("/product")
    @ApiOperation(
            value = "产品信息编辑接口",
            notes = "产品信息编辑接口，需要登录验证，" +
                    "根据提供的数据修改产品的详细信息，返回修改结果"
    )
    public Result editProductRecord(@RequestBody @Validated Product product) {
        return subjectService.requestEditProduct(product);
    }

    @GetMapping("/product/record")
    @ApiOperation(
            value = "查询产品审核分页信息接口",
            notes = "查询产品审核分页信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getProductRecordPaged(@Validated ProductQuery query) {
        return subjectService.requestProductRecordPaged(query);
    }

    @PutMapping("/product/process/batched")
    @ApiOperation(
            value = "产品信息批量审批接口",
            notes = "产品信息批量审批接口，需要登录验证，" +
                    "根据提供的数据批量审批产品的详细信息，返回修改结果"
    )
    public Result processProductRecordBatched(@RequestBody @Validated Product[] products) {
        return subjectService.requestProcessProductRecordBatched(products);
    }

    @PutMapping("/product/process/approve")
    @ApiOperation(
            value = "产品信息审批通过接口",
            notes = "产品信息审批通过接口，需要登录验证，" +
                    "根据提供的数据审批通过产品的详细信息，返回修改结果"
    )
    public Result approveProductRecord(@RequestBody @Validated Product product) {
        return subjectService.requestApproveProductRecord(product);
    }

    @PutMapping("/product/process/reject")
    @ApiOperation(
            value = "产品信息审批不通过接口",
            notes = "产品信息审批不通过接口，需要登录验证，" +
                    "根据提供的数据审批不通过产品的详细信息，返回修改结果"
    )
    public Result rejectProductRecord(@RequestBody @Validated Product product) {
        return subjectService.requestRejectProductRecord(product);
    }

}
