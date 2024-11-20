package edu.hrbu.trace_backend_business.service;

import edu.hrbu.trace_backend_business.entity.Result;
import edu.hrbu.trace_backend_business.entity.dto.segment.ApproachQuery;
import edu.hrbu.trace_backend_business.entity.dto.segment.EntranceQuery;

public interface SegmentService {

    Result requestTraceQrCode(String trace);

    Result requestApproachInfoPaged(ApproachQuery query);

    Result requestEntranceInfoPaged(EntranceQuery query);

}
