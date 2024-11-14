package edu.hrbu.trace_backend_job.service;

import edu.hrbu.trace_backend_job.entity.Result;

public interface AppletService {

    Result requestTraceProductInfo(String traceCode);

}
