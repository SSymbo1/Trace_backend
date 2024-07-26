package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MenueService {

    Result requestHomeMenue();

    Result requestSubjectMenue();

    Result requestAnalysisMenue();

    Result requestMonitorMenue();

    Result requestSegmentMenue();

    Result requestSystemMenue();

    Result requestRoleSubMenue();

    Result requestEnterpriseMenue(String keyword);

}
