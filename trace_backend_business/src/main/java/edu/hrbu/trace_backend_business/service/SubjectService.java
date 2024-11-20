package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.subject.Product;
import edu.hrbu.trace_backend_business.entity.dto.subject.ProductQuery;
import edu.hrbu.trace_backend_business.entity.dto.subject.SupplierQuery;
import edu.hrbu.trace_backend_business.entity.dto.subject.VendorsQuery;

public interface SubjectService {

    Result requestSupplierPaged(SupplierQuery query);

    Result requestVendorsPaged(VendorsQuery query);

    Result requestProductAdd(Product product);

    Result requestProductPaged(ProductQuery query);

    Result requestEditProduct(Product product);

    Result requestProductRecordPaged(ProductQuery query);

    Result requestProcessProductRecordBatched(Product[] products);

    Result requestApproveProductRecord(Product product);

    Result requestRejectProductRecord(Product product);

}
