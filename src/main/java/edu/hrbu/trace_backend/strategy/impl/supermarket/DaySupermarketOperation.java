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
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.strategy.SupermarketAnalysisStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("SuperMarketDay")
public class DaySupermarketOperation implements SupermarketAnalysisStrategy {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ClassificationMapper classificationMapper;

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
                .entranceProvinceList(getEntranceSupermarketFromDataList(query))
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
        return entranceMapper.selectAnalysisEntranceCountByYearBetween(query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue());
    }

    private List<ApproachCount> getApproachSupermarketDataList(MarketQuery query) {
        return approachMapper.selectAnalysisApproachCountByYearBetween(query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue());
    }

    private List<EntranceClassCount> getEntranceSupermarketClassDataList(MarketQuery query) {
        return entranceMapper.selectAnalysisClassCountByYearBetween(query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue());
    }

    private List<ApproachClassCount> getApproachSupermarketClassDataList(MarketQuery query) {
        return approachMapper.selectAnalysisClassCountByYearBetween(query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue());
    }

    private ProvinceData getEntranceSupermarketFromDataList(MarketQuery query) {
        Integer total = 0;
        Integer enter = 0;
        Integer outer = 0;
        Map<String, Integer> data = new HashMap<>();
        for (Province province : Province.values()) {
            data.put(province.getKey(), province.getValue());
        }
        List<ProvinceValue> provinceValues = new ArrayList<>();
        List<EntranceCount> countEntrance = entranceMapper.selectAnalysisEntranceCountByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
        for (EntranceCount count : countEntrance) {
            int entranceTotal = 0;
            int entranceEnter = 0;
            int entranceOuter = 0;
            total += count.getTotal();
            entranceTotal = count.getTotal();
            List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                    count.getDate(), count.getDate(), EnterpriseType.SHOP.getValue()
            );
            for (Entrance entrance : entrances) {
                Enterprise enterprise = enterpriseMapper.selectById(entrance.getBid());
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

    public Map<String, Integer> getEntranceSupermarketData(MarketQuery query) {
        Map<String, Integer> entrance = new HashMap<>();
        entrance.put("fresh", 0);
        entrance.put("product", 0);
        entrance.put("drink", 0);
        entrance.put("food", 0);
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
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

    public Map<String, Integer> getApproachSupermarketData(MarketQuery query) {
        Map<String, Integer> approach = new HashMap<>();
        approach.put("fresh", 0);
        approach.put("product", 0);
        approach.put("drink", 0);
        approach.put("food", 0);
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
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

    public Integer countEntranceSupermarketData(MarketQuery query) {
        return approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        ).size();
    }

    public Integer countApproachSupermarketData(MarketQuery query) {
        return entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        ).size();
    }

    private Double countEntranceSupermarketYOYData(MarketQuery query) {
        return null;
    }

    public Double countEntranceSupermarketQOQData(MarketQuery query) {
        List<Entrance> entrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Entrance> beforeEntrances = entranceMapper.selectAnalysisEntranceInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.SHOP.getValue()
        );
        return beforeEntrances.isEmpty() ?
                entrances.size() :
                (entrances.size() - beforeEntrances.size()) / (double) beforeEntrances.size();
    }

    private Double countApproachSupermarketYOYData(MarketQuery query) {
        return null;
    }

    public Double countApproachSupermarketQOQData(MarketQuery query) {
        List<Approach> approaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                query.getBefore(), query.getNow(), EnterpriseType.SHOP.getValue()
        );
        Date oneMonthBefore = DateUtil.parse(query.getBefore(), "yyyy-MM-dd");
        Date oneMonthNow = DateUtil.parse(query.getNow(), "yyyy-MM-dd");
        List<Approach> beforeApproaches = approachMapper.selectAnalysisApproachInfoByYearBetween(
                DateUtil.offsetMonth(oneMonthBefore, -1).toString(),
                DateUtil.offsetMonth(oneMonthNow, -1).toString(),
                EnterpriseType.SHOP.getValue()
        );
        return beforeApproaches.isEmpty() ?
                approaches.size() :
                (approaches.size() - beforeApproaches.size()) / (double) beforeApproaches.size();
    }

}
