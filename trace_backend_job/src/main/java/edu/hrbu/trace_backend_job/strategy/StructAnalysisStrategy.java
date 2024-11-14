package edu.hrbu.trace_backend_job.strategy;

import edu.hrbu.trace_backend_job.entity.dto.analysis.Struct;
import edu.hrbu.trace_backend_job.entity.dto.analysis.StructQuery;
import edu.hrbu.trace_backend_job.entity.po.StructReport;

import java.util.List;

public interface StructAnalysisStrategy {

    Struct getStructAnalysisDataList(StructQuery query);

    List<StructReport> getStructAnalysisReport(StructQuery query);

}
