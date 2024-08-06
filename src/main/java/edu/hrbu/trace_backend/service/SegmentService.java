package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.segment.ApproachQuery;
import edu.hrbu.trace_backend.entity.dto.segment.EntranceQuery;

public interface SegmentService {

    Result requestTraceQrCode(String trace);

    Result requestApproachInfoPaged(ApproachQuery query);

    Result requestEntranceInfoPaged(EntranceQuery query);

}
