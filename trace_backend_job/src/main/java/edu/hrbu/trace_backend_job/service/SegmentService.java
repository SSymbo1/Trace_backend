package edu.hrbu.trace_backend_job.service;

import edu.hrbu.trace_backend_job.entity.Result;
import edu.hrbu.trace_backend_job.entity.dto.segment.ApproachQuery;
import edu.hrbu.trace_backend_job.entity.dto.segment.EntranceQuery;

public interface SegmentService {

    Result requestTraceQrCode(String trace);

    Result requestApproachInfoPaged(ApproachQuery query);

    Result requestEntranceInfoPaged(EntranceQuery query);

}
