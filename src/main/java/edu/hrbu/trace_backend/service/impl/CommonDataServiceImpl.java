package edu.hrbu.trace_backend.service.impl;

import edu.hrbu.trace_backend.entity.Result;
import edu.hrbu.trace_backend.service.CommonDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonDataServiceImpl implements CommonDataService {

    @Override
    public Result requestHomeStatisticsCardData() {
       return null;
    }

    @Override
    public Result requestHomeStatisticsLineData() {
        return null;
    }

}
