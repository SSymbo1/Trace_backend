package edu.hrbu.trace_backend_business.strategy.impl.farm;

import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend_business.entity.dto.analysis.*;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.po.Approach;
import edu.hrbu.trace_backend_business.entity.po.Classification;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import edu.hrbu.trace_backend_business.entity.po.Entrance;
import edu.hrbu.trace_backend_business.mapper.ApproachMapper;
import edu.hrbu.trace_backend_business.mapper.ClassificationMapper;
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.EntranceMapper;
import edu.hrbu.trace_backend_business.strategy.FarmAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("FarmDay")
public class DayFarmOperation implements FarmAnalysisStrategy {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ClassificationMapper classificationMapper;

    @Override
    public Result getFarmAnalysisData(FarmQuery query) {
        Map<String, Integer> countEntrance = getEntranceFarmData(query);
        return Farm.builder()
                .entranceSum(countEntranceFarmData(query))
                .approachSum(countApproachFarmData(query))
                .entranceQOQ(countEntranceFarmQOQData(query))
                .entranceYOY(countEntranceFarmYOYData(query))
                .approachQOQ(countApproachFarmQOQData(query))
                .approachYOY(countApproachFarmYOYData(query))
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food"))
                .provinceDataList(getApproachFarmFromDataList(query))
                .entranceTotalList(getEntranceFarmDataList(query))
                .approachTotalList(getApproachFarmDataList(query))
                .entranceClassList(getEntranceFarmClassDataList(query))
                .rankList(getFarmEnterpriseSellRank(query)).build();
    }

    Integer countApproachFarmData(FarmQuery query) {
        return approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        ).size();
    }

    Integer countEntranceFarmData(FarmQuery query) {
        return entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        ).size();
    }

    Double countApproachFarmQOQData(FarmQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.FARM.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();
    }

    private Double countApproachFarmYOYData(FarmQuery query) {
        return null;
    }

    Double countEntranceFarmQOQData(FarmQuery query) {
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.FARM.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    private Double countEntranceFarmYOYData(FarmQuery query) {
        return null;
    }

    private ProvinceData getApproachFarmFromDataList(FarmQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<ApproachCount> countEntrance = approachMapper.selectAnalysisApproachCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
        for (ApproachCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal = count.getTotal();
            List<Approach> entrances = approachMapper.selectAnalysisApproachInfoByYearBetween(
                    count.getDate(), count.getDate(), EnterpriseType.FARM.getValue()
            );
            for (Approach approach : entrances) {
                Enterprise enterprise = enterpriseMapper.selectById(approach.getAid());
                if (enterprise.getAddress().contains(edu.hrbu.trace_backend_business.entity.enums.Province.HEILONGJIANG.getKey())) {
                    entranceEnter++;
                    enter++;
                } else {
                    entranceOuter++;
                    outer++;
                }
                for (edu.hrbu.trace_backend_business.entity.enums.Province province : edu.hrbu.trace_backend_business.entity.enums.Province.values()) {
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
        List<Province> provinceList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            provinceList.add(Province.builder()
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

    private List<EntranceCount> getEntranceFarmDataList(FarmQuery query) {
        return entranceMapper.selectAnalysisEntranceCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
    }

    private List<ApproachCount> getApproachFarmDataList(FarmQuery query) {
        return approachMapper.selectAnalysisApproachCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
    }

    private List<EntranceClassCount> getEntranceFarmClassDataList(FarmQuery query) {
        return entranceMapper.selectAnalysisClassCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
    }

    List<Rank> getFarmEnterpriseSellRank(FarmQuery query) {
        return entranceMapper.selectEntranceSellRankByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
        );
    }

    Map<String, Integer> getEntranceFarmData(FarmQuery query) {
        Map<String, Integer> entrance = new HashMap<>();
        entrance.put("fresh", 0);
        entrance.put("product", 0);
        entrance.put("drink", 0);
        entrance.put("food", 0);
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.FARM.getValue()
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
