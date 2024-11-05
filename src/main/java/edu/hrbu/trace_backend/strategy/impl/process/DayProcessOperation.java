package edu.hrbu.trace_backend.strategy.impl.process;

import edu.hrbu.trace_backend.entity.dto.analysis.Process;
import edu.hrbu.trace_backend.entity.dto.analysis.ProcessQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.Rank;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend.entity.po.Classification;
import edu.hrbu.trace_backend.entity.po.Entrance;
import edu.hrbu.trace_backend.entity.po.EntranceClassCount;
import edu.hrbu.trace_backend.mapper.ClassificationMapper;
import edu.hrbu.trace_backend.mapper.EntranceMapper;
import edu.hrbu.trace_backend.strategy.ProcessAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("ProcessDay")
public class DayProcessOperation implements ProcessAnalysisStrategy {

    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private ClassificationMapper classificationMapper;

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
        return entranceMapper.selectAnalysisClassCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PRODUCT.getValue()
        );
    }

    List<Rank> getProcessEnterpriseSellRank(ProcessQuery query) {
        return entranceMapper.selectEntranceSellRankByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PRODUCT.getValue()
        );
    }

    Map<String, Integer> getEntranceBatchData(ProcessQuery query) {
        Map<String, Integer> entrance = new HashMap<>();
        entrance.put("fresh", 0);
        entrance.put("product", 0);
        entrance.put("drink", 0);
        entrance.put("food", 0);
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PRODUCT.getValue()
        );
        for (Entrance count : entrances) {
            Classification classification = classificationMapper.selectById(count.getCid());
            if (classification.getParent() == 1) {
                entrance.put("fresh", entrance.get("fresh") + 1);
            } else if (classification.getParent() == 2) {
                entrance.put("product", entrance.get("product") + 1);
            } else if (classification.getParent() == 3) {
                entrance.put("drink", entrance.get("drink") + 1);
            } else if (classification.getParent() == 4) {
                entrance.put("food", entrance.get("food") + 1);
            }
        }
        return entrance;
    }

}
