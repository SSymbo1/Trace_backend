package edu.hrbu.trace_backend.strategy.impl.butch;

import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend.entity.dto.analysis.*;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend.entity.enums.Format;
import edu.hrbu.trace_backend.entity.enums.Province;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.mapper.ApproachMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.EntranceMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("ButchMonth")
public class MonthButchOperation extends DayButchOperation {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Result getButchAnalysisData(ButchQuery query) {
        Map<String, Integer> countEntrance = getEntranceButchData(query);
        return Butch.builder()
                .entranceSum(countEntranceButchData(query))
                .approachSum(countApproachButchData(query))
                .entranceQOQ(countEntranceButchQOQData(query))
                .entranceYOY(countEntranceButchYOYData(query))
                .approachQOQ(countApproachButchQOQData(query))
                .approachYOY(countApproachButchYOYData(query))
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food"))
                .provinceDataList(getApproachButchFromDataList(query))
                .entranceTotalList(getEntranceButchDataList(query))
                .approachTotalList(getApproachButchDataList(query))
                .entranceClassList(getEntranceButchClassDataList(query))
                .rankList(getButchEnterpriseSellRank(query)).build();
    }

    private ProvinceData getApproachButchFromDataList(ButchQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (Province province : Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<ApproachCount> countEntrance = approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BUTCH.getValue()
        );
        for (ApproachCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal += count.getTotal();
            List<Approach> entrances = approachMapper.selectAnalysisApproachInfoMonthByYearBetween(
                    count.getDate(), EnterpriseType.BUTCH.getValue()
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

    private List<EntranceCount> getEntranceButchDataList(ButchQuery query) {
        return entranceMapper.selectAnalysisEntranceMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BUTCH.getValue()
        );
    }

    private List<ApproachCount> getApproachButchDataList(ButchQuery query) {
        return approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BUTCH.getValue()
        );
    }

    private List<EntranceClassCount> getEntranceButchClassDataList(ButchQuery query) {
        return entranceMapper.selectAnalysisClassCountMonthByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BUTCH.getValue()
        );
    }

    private Double countEntranceButchYOYData(ButchQuery query){
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.BUTCH.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.BUTCH.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    private Double countApproachButchYOYData(ButchQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(),
                EnterpriseType.BUTCH.getValue());
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), Format.FULL_DATE_FORMAT.getValue());
        Date oneMonthNow = DateUtil.parse(query.getNow(), Format.FULL_DATE_FORMAT.getValue());
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.BUTCH.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();
    }

}
