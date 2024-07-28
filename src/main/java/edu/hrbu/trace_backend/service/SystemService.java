package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.*;
import org.springframework.transaction.annotation.Transactional;

public interface SystemService {

    Result requestAccountInfoPaged(UserQuery query);

    Result requestAccountAdd(Account account);

    Result requestAccountStatueSet(AccountStatue statue);

    Result requestAccountEdit(Account account);

    Result requestSensitiveAccountInfoPaged(String keyword,Integer currentPage,Integer pageSize);

    Result requestSensitiveEnterpriseInfoPaged(String keyword,Integer currentPage,Integer pageSize);

    Result requestEnterpriseInfoPaged(EnterpriseQuery query);

    Result requestEnterpriseAdd(Enterprise enterprise);

    Result requestEnterpriseEdit(Enterprise enterprise);

}
