package edu.hrbu.trace_backend.service;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.entity.dto.Decode;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommonDataService {

    Result requestHomeStatisticsCardData();

    Result requestHomeStatisticsLineData();

    Result requestWhoIs();

    Result requestDecodePass(Decode decode);

    Result requestEditInfo(Integer accountId);

}
