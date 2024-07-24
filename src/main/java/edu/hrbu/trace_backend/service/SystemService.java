package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.UserQuery;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SystemService {

    Result requestAccountInfoPaged(UserQuery query);

}
