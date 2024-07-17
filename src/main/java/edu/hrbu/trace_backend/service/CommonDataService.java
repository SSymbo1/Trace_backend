package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommonDataService {

    Result requestHomeStatisticsCardData();

    Result requestHomeStatisticsLineData();

}
