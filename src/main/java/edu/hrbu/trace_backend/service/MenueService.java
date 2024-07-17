package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MenueService {

    Result requestHomeMenue();

    Result requestSubjectMenue();

    Result requestQualityMenue();

    Result requestEmergencyMenue();

    Result requestAnalysisMenue();

    Result requestConstructMenue();

    Result requestDeviceMenue();

    Result requestMonitorMenue();

    Result requestSegmentMenue();

    Result requestOperationMenue();

    Result requestSystemMenue();

}
