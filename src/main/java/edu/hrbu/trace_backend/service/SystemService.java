package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.system.*;

public interface SystemService {

    Result requestAccountInfoPaged(UserQuery query);

    Result requestAccountAdd(Account account);

    Result requestAccountStatueSet(AccountStatue statue);

    Result requestAccountEdit(Account account);

    Result requestEnableAllAccount(Able able);

    Result requestDisableAllAccount(Able able);

    Result requestEnterpriseInfoPaged(EnterpriseQuery query);

    Result requestEnterpriseAdd(Enterprise enterprise);

    Result requestEnterpriseEdit(Enterprise enterprise);

    Result requestRoleInfoPaged(RoleQuery query);

    Result requestRoleAdd(Role role);

    Result requestRoleEdit(Role role);

    Result requestRoleStatueSet(RoleStatue statue);

    Result requestEnableAllRole(Able able);

    Result requestDisableAllRole(Able able);

    Result requestSensitiveAccountInfoPaged(String keyword, Integer currentPage, Integer pageSize);

    Result requestSensitiveEnterpriseInfoPaged(String keyword, Integer currentPage, Integer pageSize);

    Result requestSensitiveRoleInfoPaged(String keyword, Integer currentPage, Integer pageSize);

}
