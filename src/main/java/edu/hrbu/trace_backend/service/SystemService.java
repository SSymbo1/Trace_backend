package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Account;
import edu.hrbu.trace_backend.entity.dto.AccountStatue;
import edu.hrbu.trace_backend.entity.dto.UserQuery;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SystemService {

    Result requestAccountInfoPaged(UserQuery query);

    Result requestAccountAdd(Account account);

    Result requestAccountStatueSet(AccountStatue statue);

    Result requestAccountEdit(Account account);

    Result requestSensitiveAccountInfoPaged(String keyword,Integer currentPage,Integer pageSize);

}
