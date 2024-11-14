package edu.hrbu.trace_backend_job.service;

import edu.hrbu.trace_backend_job.entity.Result;

public interface MenueService {

    Result requestHomeMenue();

    Result requestSubjectMenue();

    Result requestAnalysisMenue();

    Result requestMonitorMenue();

    Result requestSegmentMenue();

    Result requestSystemMenue();

    Result requestRoleSubMenue();

    Result requestEnterpriseMenue(String keyword);

    Result requestRoleTreeMenue();

    Result requestClassificationTreeMenue();

}
