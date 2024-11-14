package edu.hrbu.trace_backend_job.strategy.impl.process;

import edu.hrbu.trace_backend_job.entity.dto.analysis.Process;
import edu.hrbu.trace_backend_job.entity.dto.analysis.ProcessQuery;
import edu.hrbu.trace_backend_job.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_job.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_job.entity.dto.analysis.EntranceClassCount;
import edu.hrbu.trace_backend_job.mapper.EntranceMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component("ProcessMonth")
public class MonthProcessOperation extends DayProcessOperation {

    @Resource
    private EntranceMapper entranceMapper;

    @Override
    public Result getProcessAnalysisData(ProcessQuery query) {
        Map<String, Integer> countEntrance = getEntranceBatchData(query);
        return Process.builder()
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food"))
                .entranceClassList(getEntranceProcessClassCount(query))
                .rankList(getProcessEnterpriseSellRank(query)).build();
    }

    private List<EntranceClassCount> getEntranceProcessClassCount(ProcessQuery query) {
        return entranceMapper.selectAnalysisClassCountMonthByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PRODUCT.getValue()
        );
    }

}
