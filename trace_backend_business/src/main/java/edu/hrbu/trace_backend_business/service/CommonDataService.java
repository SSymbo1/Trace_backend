package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.Decode;

public interface CommonDataService {

    Result requestHomeStatisticsCardData();

    Result requestHomeStatisticsLineData();

    Result requestWhoIs();

    Result requestDecodePass(Decode decode);

    Result requestEditAccountInfo(Integer accountId);

    Result requestEditEnterpriseInfo(Integer enterpriseId);

    Result requestEditRoleInfo(Integer roleId);

    Result requestProductInfo(Integer productId);

    Result requestApproveInfo(Integer approverId);
}
