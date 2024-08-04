package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Decode;
import io.swagger.models.auth.In;
import org.springframework.transaction.annotation.Transactional;

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
