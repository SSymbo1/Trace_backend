package edu.hrbu.trace_backend_business.strategy;

import edu.hrbu.trace_backend_business.entity.dto.analysis.Struct;
import edu.hrbu.trace_backend_business.entity.dto.analysis.StructQuery;
import edu.hrbu.trace_backend_business.entity.po.StructReport;

import java.util.List;

public interface StructAnalysisStrategy {

    Struct getStructAnalysisDataList(StructQuery query);

    List<StructReport> getStructAnalysisReport(StructQuery query);

}
