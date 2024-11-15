package edu.hrbu.trace_backend_business.strategy.impl.plant;

import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend_business.entity.dto.analysis.*;
import edu.hrbu.trace_backend_business.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend_business.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend_business.entity.enums.Format;
import edu.hrbu.trace_backend_business.entity.enums.Province;
import edu.hrbu.trace_backend_business.entity.po.Approach;
import edu.hrbu.trace_backend_business.entity.po.Enterprise;
import edu.hrbu.trace_backend_business.entity.po.Entrance;
import edu.hrbu.trace_backend_business.mapper.ApproachMapper;
import edu.hrbu.trace_backend_business.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend_business.mapper.EntranceMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("PlantMonth")
public class MonthPlantOperation extends DayPlantOperation {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Result getPlantAnalysisData(PlantQuery query) {
        Map<String, Integer> countEntrance = getEntrancePlantData(query);
        return Plant.builder()
                .entranceSum(countEntrancePlantData(query))
                .approachSum(countApproachPlantData(query))
                .entranceQOQ(countEntrancePlantQOQData(query))
                .entranceYOY(countEntrancePlantYOYData(query))
                .approachQOQ(countApproachPlantQOQData(query))
                .approachYOY(countApproachPlantYOYData(query))
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food"))
                .provinceDataList(getApproachPlantFromDataList(query))
                .entranceTotalList(getEntrancePlantDataList(query))
                .approachTotalList(getApproachPlantDataList(query))
                .entranceClassList(getEntrancePlantClassDataList(query))
                .rankList(getPlantEnterpriseSellRank(query)).build();
    }

    private ProvinceData getApproachPlantFromDataList(PlantQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (Province province : Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<ApproachCount> countEntrance = approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PLANT.getValue()
        );
        for (ApproachCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal += count.getTotal();
            List<Approach> entrances = approachMapper.selectAnalysisApproachInfoMonthByYearBetween(
                    count.getDate(), EnterpriseType.PLANT.getValue()
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
        List<edu.hrbu.trace_backend_business.entity.dto.analysis.Province> provinceList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            provinceList.add(edu.hrbu.trace_backend_business.entity.dto.analysis.Province.builder()
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

    private List<EntranceCount> getEntrancePlantDataList(PlantQuery query) {
        return entranceMapper.selectAnalysisEntranceMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PLANT.getValue()
        );
    }

    private List<ApproachCount> getApproachPlantDataList(PlantQuery query) {
        return approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PLANT.getValue()
        );
    }

    private List<EntranceClassCount> getEntrancePlantClassDataList(PlantQuery query) {
        return entranceMapper.selectAnalysisClassCountMonthByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PLANT.getValue()
        );
    }

    private Double countEntrancePlantYOYData(PlantQuery query){
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.PLANT.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.PLANT.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    private Double countApproachPlantYOYData(PlantQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(),
                EnterpriseType.PLANT.getValue());
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.PLANT.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();
    }
}
