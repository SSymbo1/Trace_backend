package edu.hrbu.trace_backend.strategy.impl.supermarket;

import cn.hutool.core.date.DateUtil;
import edu.hrbu.trace_backend.entity.dto.analysis.MarketQuery;
import edu.hrbu.trace_backend.entity.dto.analysis.ProvinceData;
import edu.hrbu.trace_backend.entity.dto.analysis.ProvinceValue;
import edu.hrbu.trace_backend.entity.dto.analysis.Supermarket;
import edu.hrbu.trace_backend.entity.dto.analysis.base.Result;
import edu.hrbu.trace_backend.entity.enums.EnterpriseType;
import edu.hrbu.trace_backend.entity.enums.Province;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.ApproachMapper;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.EntranceMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("SuperMarketMonth")
public class MonthSupermarketOperation extends DaySupermarketOperation {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Override
    public Result getSuperMarketAnalysisData(MarketQuery query) {
        Map<String, Integer> countEntrance = getEntranceSupermarketData(query);
        Map<String, Integer> countApproach = getApproachSupermarketData(query);
        return Supermarket.builder()
                .approachSum(countApproachSupermarketData(query))
                .approachQOQ(countApproachSupermarketQOQData(query))
                .approachYOY(countApproachSupermarketYOYData(query))
                .entranceSum(countEntranceSupermarketData(query))
                .entranceQOQ(countEntranceSupermarketQOQData(query))
                .entranceYOY(countEntranceSupermarketYOYData(query))
                .entranceTotalList(getEntranceSupermarketDataList(query))
                .entranceClassList(getEntranceSupermarketClassDataList(query))
                .provinceDataList(getApproachSupermarketFromDataList(query))
                .approachTotalList(getApproachSupermarketDataList(query))
                .approachClassList(getApproachSupermarketClassDataList(query))
                .approachFresh(countApproach.get("fresh"))
                .approachProduct(countApproach.get("product"))
                .approachDrink(countApproach.get("drink"))
                .approachFood(countApproach.get("food"))
                .entranceFresh(countEntrance.get("fresh"))
                .entranceProduct(countEntrance.get("product"))
                .entranceDrink(countEntrance.get("drink"))
                .entranceFood(countEntrance.get("food")).build();
    }

    private List<EntranceCount> getEntranceSupermarketDataList(MarketQuery query) {
        return entranceMapper.selectAnalysisEntranceMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
    }

    private List<ApproachCount> getApproachSupermarketDataList(MarketQuery query) {
        return approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
    }

    private List<EntranceClassCount> getEntranceSupermarketClassDataList(MarketQuery query) {
        return entranceMapper.selectAnalysisClassCountMonthByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
    }

    private List<ApproachClassCount> getApproachSupermarketClassDataList(MarketQuery query) {
        return approachMapper.selectAnalysisClassCountMonthByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
    }

    private ProvinceData getApproachSupermarketFromDataList(MarketQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (Province province : Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<ApproachCount> countEntrance = approachMapper.selectAnalysisApproachMonthCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
        for (ApproachCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal += count.getTotal();
            List<Approach> entrances = approachMapper.selectAnalysisApproachInfoMonthByYearBetween(
                    count.getDate(), EnterpriseType.SHOP.getValue()
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

    private Double countEntranceSupermarketYOYData(MarketQuery query) {
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.SHOP.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    private Double countApproachSupermarketYOYData(MarketQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(),
                EnterpriseType.SHOP.getValue());
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -12).toString(),
                DateUtil.offsetMonth(oneMonthNow, -12).toString(),
                EnterpriseType.SHOP.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();

    }

}
