package edu.hrbu.trace_backend.strategy.impl.batch;

import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend.entity.dto.analysis.Batch;
import edu.hrbu.trace_backend.entity.dto.analysis.BatchQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.ProvinceData;
import edu.hrbu.trace_backend.entity.dto.analysis.ProvinceValue;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend.entity.enums.Province;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.ApproachMapper;
import edu.hrbu.trace_backend.mapper.ClassificationMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.EntranceMapper;
import edu.hrbu.trace_backend.strategy.BatchAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("BatchDay")
public class DayBatchOperation implements BatchAnalysisStrategy {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ClassificationMapper classificationMapper;

    @Override
    public Result getBatchAnalysisData(BatchQuery query) {
        Map<String, Integer> countEntrance = getEntranceBatchData(query);
        Map<String, Integer> countApproach = getApproachBatchData(query);
        return Batch.builder()
                .approachSum(countApproachBatchData(query))
                .approachQOQ(countApproachBatchQOQData(query))
                .approachYOY(countApproachBatchYOYData(query))
                .entranceSum(countEntranceBatchData(query))
                .entranceQOQ(countEntranceBatchQOQData(query))
                .entranceYOY(countEntranceBatchYOYData(query))
                .entranceTotalList(getEntranceBatchDataList(query))
                .entranceClassList(getEntranceBatchClassDataList(query))
                .provinceDataList(getApproachBatchFromDataList(query))
                .approachTotalList(getApproachBatchDataList(query))
                .approachClassList(getApproachBatchClassDataList(query))
                .approachFresh(countApproach.get("fresh"))
                .approachProduct(countApproach.get("product"))
                .approachDrink(countApproach.get("drink"))
                .approachFood(countApproach.get("food"))
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food")).build();
    }

    private List<ApproachClassCount> getApproachBatchClassDataList(BatchQuery query) {
        return approachMapper.selectAnalysisClassCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
    }

    Map<String, Integer> getApproachBatchData(BatchQuery query) {
        Map<String, Integer> approach = new HashMap<>();
        approach.put("fresh", 0);
        approach.put("product", 0);
        approach.put("drink", 0);
        approach.put("food", 0);
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
        for (Approach count : approaches) {
            Classification classification = classificationMapper.selectById(count.getCid());
            if (classification.getParent() == 1) {
                approach.put("fresh", approach.get("fresh") + 1);
            } else if (classification.getParent() == 2) {
                approach.put("product", approach.get("product") + 1);
            } else if (classification.getParent() == 3) {
                approach.put("drink", approach.get("drink") + 1);
            } else if (classification.getParent() == 4)
                approach.put("food", approach.get("food") + 1);
        }
        return approach;
    }

    Map<String, Integer> getEntranceBatchData(BatchQuery query) {
        Map<String, Integer> entrance = new HashMap<>();
        entrance.put("fresh", 0);
        entrance.put("product", 0);
        entrance.put("drink", 0);
        entrance.put("food", 0);
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
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

    private List<ApproachCount> getApproachBatchDataList(BatchQuery query) {
        return approachMapper.selectAnalysisApproachCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
    }

    private ProvinceData getApproachBatchFromDataList(BatchQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (Province province : Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<ApproachCount> countEntrance = approachMapper.selectAnalysisApproachCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
        for (ApproachCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal = count.getTotal();
            List<Approach> entrances = approachMapper.selectAnalysisApproachInfoByYearBetween(
                    count.getDate(), count.getDate(), EnterpriseType.BATCH.getValue()
            );
            for (Approach approach : entrances) {
                Enterprise enterprise = enterpriseMapper.selectById(approach.getAid());
                if (enterprise.getAddress().contains(Province.HEILONGJIANG.getKey())) {
                    entranceEnter++;
                    enter++;
                } else {
                    entranceOuter++;
                    outer++;
                }
                for (Province province : Province.values()) {
                    if (enterprise.getAddress().contains(province.getKey())) {
                        data.put(province.getKey(), data.get(province.getKey()) + 1);
                    }
                }
            }
            ProvinceValue provinceValue = ProvinceValue.builder()
                    .date(count.getDate())
                    .enter(entranceEnter)
                    .outer(entranceOuter)
                    .total(entranceTotal).build();
            provinceValues.add(provinceValue);
        }
        List<edu.hrbu.trace_backend.entity.dto.analysis.Province> provinceList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            provinceList.add(edu.hrbu.trace_backend.entity.dto.analysis.Province.builder()
                    .name(entry.getKey())
                    .value(entry.getValue()).build());
        }
        return ProvinceData.builder()
                .total(total)
                .enter(total == 0 ? 0 : (double) (enter / total))
                .outer(total == 0 ? 0 : (double) (outer / total))
                .values(provinceValues)
                .provinces(provinceList).build();
    }

    private List<EntranceClassCount> getEntranceBatchClassDataList(BatchQuery query) {
        return entranceMapper.selectAnalysisClassCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
    }

    private List<EntranceCount> getEntranceBatchDataList(BatchQuery query) {
        return entranceMapper.selectAnalysisEntranceCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
    }

    private Double countEntranceBatchYOYData(BatchQuery query) {
        return null;
    }

    Double countEntranceBatchQOQData(BatchQuery query) {
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.BATCH.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    Integer countEntranceBatchData(BatchQuery query) {
        return entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        ).size();
    }

    private Double countApproachBatchYOYData(BatchQuery query) {
        return null;
    }

    Double countApproachBatchQOQData(BatchQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.BATCH.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();
    }

    Integer countApproachBatchData(BatchQuery query) {
        return approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BATCH.getValue()
        ).size();
    }
}
