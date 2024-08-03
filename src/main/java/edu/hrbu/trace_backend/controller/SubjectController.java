package edu.hrbu.trace_backend.controller;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.subject.Product;
import edu.hrbu.trace_backend.entity.dto.subject.ProductQuery;
import edu.hrbu.trace_backend.entity.dto.subject.SupplierQuery;
import edu.hrbu.trace_backend.entity.dto.subject.VendorsQuery;
import edu.hrbu.trace_backend.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Pointcut;
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
    public Result getSupplierPaged(SupplierQuery query) {
        return subjectService.requestSupplierPaged(query);
    }

    @GetMapping("/vendors")
    @ApiOperation(
            value = "查询分页供销商信息接口",
            notes = "查询分页供销商信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getVendorsPaged(VendorsQuery query){
        return subjectService.requestVendorsPaged(query);
    }

    @GetMapping("/product")
    @ApiOperation(
            value = "查询产品分页信息接口",
            notes = "查询产品分页信息接口，需要登录验证，" +
                    "除了分页参数外，不携带条件时全查询，携带条件则进行条件查询"
    )
    public Result getProductPaged(ProductQuery query){
        return subjectService.requestProductPaged(query);
    }

    @PostMapping("/product")
    @ApiOperation(
            value = "添加产品备案接口",
            notes = "添加产品备案接口，需要登录验证，" +
                    "提供产品备案所需要的信息，返回执行结果"
    )
    public Result addProductRecord(@RequestBody Product product){
        return subjectService.requestProductAdd(product);
    }

}
