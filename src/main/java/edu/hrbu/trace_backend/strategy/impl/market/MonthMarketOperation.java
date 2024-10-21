package edu.hrbu.trace_backend.strategy.impl.market;

import edu.hrbu.trace_backend.entity.dto.analysis.OperationsQuery;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.*;
import edu.hrbu.trace_backend.strategy.MarketOperationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("month")
public class MonthMarketOperation implements MarketOperationStrategy {
    @Resource
    private ApproachMapper approachMapper;
    @Resource
    private EntranceMapper entranceMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private ProductRecordMapper productRecordMapper;
    @Resource
    private ProductMapper productMapper;
    private final Map<String, Integer> plantBusiness = new HashMap<>();
    private final Map<String, Integer> animalBusiness = new HashMap<>();
    private final Map<String, Integer> productBusiness = new HashMap<>();
    private final Map<String, Integer> butchBusiness = new HashMap<>();
    private final Map<String, Integer> batchBusiness = new HashMap<>();
    private final Map<String, Integer> farmBusiness = new HashMap<>();
    private final Map<String, Integer> shopBusiness = new HashMap<>();
    private final Map<String, Integer> unitBusiness = new HashMap<>();

    @Override
    public List<Map<String, Integer>> getMarketOperationOriginData(OperationsQuery query) {
        initCountMap();
        List<Approach> approaches = approachMapper.selectApproachInfoByYearBetween(query.getYear() + "-1-1", query.getYear() + "-12-31");
        List<Entrance> entrances = entranceMapper.selectEntranceInfoByYearBetween(query.getYear() + "-1-1", query.getYear() + "-12-31");
        List<ProductRecord> processed = productRecordMapper.selectProcessedInfoByYearBetween(query.getYear() + "-1-1", query.getYear() + "-12-31");
        List<ProductRecord> insert = productRecordMapper.selectInsertInfoByYearBetween(query.getYear() + "-1-1", query.getYear() + "-12-31");
        approaches.forEach(approach -> {
            Enterprise enterprise = enterpriseMapper.selectById(approach.getEid());
            countBusiness(enterprise, approach.getBusinessTime());
        });
        entrances.forEach(entrance -> {
            Enterprise enterprise = enterpriseMapper.selectById(entrance.getBid());
            countBusiness(enterprise, entrance.getBusinessTime());
        });
        processed.forEach(record -> {
            Product product = productMapper.selectById(record.getRid());
            Enterprise enterprise = enterpriseMapper.selectById(product.getEid());
            countBusiness(enterprise, record.getProcessTime());
        });
        insert.forEach(record -> {
            Product product = productMapper.selectById(record.getRid());
            Enterprise enterprise = enterpriseMapper.selectById(product.getEid());
            countBusiness(enterprise, record.getInsertTime());
        });
        return dataCollect();
    }

    @Override
    public List<Map<String, Double>> getMarketOperationQOQData(OperationsQuery query, List<Map<String, Integer>> origin) {
        List<Map<String, Double>> data = new ArrayList<>();
        OperationsQuery operationsQuery = OperationsQuery.builder()
                .year(String.valueOf(Integer.parseInt(query.getYear()) - 1)).build();
        List<Map<String, Integer>> beforeYearMonthData = getMarketOperationOriginData(operationsQuery);
        Map<String, Map<String, Integer>> template = new HashMap<>();
        Map<String, Map<String, Integer>> templateBefore = new HashMap<>();
        origin.forEach(o -> {
            Map<String, Integer> temp = new HashMap<>();
            temp.put("plant", o.get("plant"));
            temp.put("animal", o.get("animal"));
            temp.put("product", o.get("product"));
            temp.put("butch", o.get("butch"));
            temp.put("batch", o.get("batch"));
            temp.put("farm", o.get("farm"));
            temp.put("shop", o.get("shop"));
            temp.put("unit", o.get("unit"));
            template.put(String.valueOf(o.get("time")), temp);
        });
        beforeYearMonthData.forEach(b -> {
            Map<String, Integer> temp = new HashMap<>();
            temp.put("plant", b.get("plant"));
            temp.put("animal", b.get("animal"));
            temp.put("product", b.get("product"));
            temp.put("butch", b.get("butch"));
            temp.put("batch", b.get("batch"));
            temp.put("farm", b.get("farm"));
            temp.put("shop", b.get("shop"));
            temp.put("unit", b.get("unit"));
            templateBefore.put(String.valueOf(b.get("time")), temp);
        });
        for (int month = 12; month >= 1; month--) {
            Map<String, Double> dataMap = new HashMap<>();
            dataMap.put("time", (double) month);
            dataMap.put("plant", (double) (templateBefore.get(String.valueOf(month)).get("plant") == 0 ? template.get(String.valueOf(month)).get("plant") : (template.get(String.valueOf(month)).get("plant") - templateBefore.get(String.valueOf(month)).get("plant")) / templateBefore.get(String.valueOf(month)).get("plant")));
            dataMap.put("animal", (double) (templateBefore.get(String.valueOf(month)).get("animal") == 0 ? template.get(String.valueOf(month)).get("animal") : (template.get(String.valueOf(month)).get("animal") - templateBefore.get(String.valueOf(month)).get("animal")) / templateBefore.get(String.valueOf(month)).get("animal")));
            dataMap.put("product", (double) (templateBefore.get(String.valueOf(month)).get("product") == 0 ? template.get(String.valueOf(month)).get("product") : (template.get(String.valueOf(month)).get("product") - templateBefore.get(String.valueOf(month)).get("product")) / templateBefore.get(String.valueOf(month)).get("product")));
            dataMap.put("butch", (double) (templateBefore.get(String.valueOf(month)).get("butch") == 0 ? template.get(String.valueOf(month)).get("butch") : (template.get(String.valueOf(month)).get("butch") - templateBefore.get(String.valueOf(month)).get("butch")) / templateBefore.get(String.valueOf(month)).get("butch")));
            dataMap.put("batch", (double) (templateBefore.get(String.valueOf(month)).get("batch") == 0 ? template.get(String.valueOf(month)).get("batch") : (template.get(String.valueOf(month)).get("batch") - templateBefore.get(String.valueOf(month)).get("batch")) / templateBefore.get(String.valueOf(month)).get("batch")));
            dataMap.put("farm", (double) (templateBefore.get(String.valueOf(month)).get("farm") == 0 ? template.get(String.valueOf(month)).get("farm") : (template.get(String.valueOf(month)).get("farm") - templateBefore.get(String.valueOf(month)).get("farm")) / templateBefore.get(String.valueOf(month)).get("farm")));
            dataMap.put("shop", (double) (templateBefore.get(String.valueOf(month)).get("shop") == 0 ? template.get(String.valueOf(month)).get("shop") : (template.get(String.valueOf(month)).get("shop") - templateBefore.get(String.valueOf(month)).get("shop")) / templateBefore.get(String.valueOf(month)).get("shop")));
            dataMap.put("unit", (double) (templateBefore.get(String.valueOf(month)).get("unit") == 0 ? template.get(String.valueOf(month)).get("unit") : (template.get(String.valueOf(month)).get("unit") - templateBefore.get(String.valueOf(month)).get("unit")) / templateBefore.get(String.valueOf(month)).get("unit")));
            data.add(dataMap);
        }
        return data;
    }

    @Override
    public List<Map<String, Double>> getMarketOperationYOYData(OperationsQuery query, List<Map<String, Integer>> origin) {
        List<Map<String, Double>> data = new ArrayList<>();
        Map<String, Map<String, Integer>> template = new HashMap<>();
        origin.forEach(o -> {
            Map<String, Integer> temp = new HashMap<>();
            temp.put("plant", o.get("plant"));
            temp.put("animal", o.get("animal"));
            temp.put("product", o.get("product"));
            temp.put("butch", o.get("butch"));
            temp.put("batch", o.get("batch"));
            temp.put("farm", o.get("farm"));
            temp.put("shop", o.get("shop"));
            temp.put("unit", o.get("unit"));
            template.put(String.valueOf(o.get("time")), temp);
        });
        for (int month = 12; month >= 1; month--) {
            Map<String, Double> dataMap = new HashMap<>();
            dataMap.put("time", (double) month);
            if (month != 1) {
                dataMap.put("plant", (double) (template.get(String.valueOf(month - 1)).get("plant") == 0 ? template.get(String.valueOf(month)).get("plant") : (template.get(String.valueOf(month)).get("plant") - template.get(String.valueOf(month - 1)).get("plant")) / template.get(String.valueOf(month - 1)).get("plant")));
                dataMap.put("animal", (double) (template.get(String.valueOf(month - 1)).get("animal") == 0 ? template.get(String.valueOf(month)).get("animal") : (template.get(String.valueOf(month)).get("animal") - template.get(String.valueOf(month - 1)).get("animal")) / template.get(String.valueOf(month - 1)).get("animal")));
                dataMap.put("product", (double) (template.get(String.valueOf(month - 1)).get("product") == 0 ? template.get(String.valueOf(month)).get("product") : (template.get(String.valueOf(month)).get("product") - template.get(String.valueOf(month - 1)).get("product")) / template.get(String.valueOf(month - 1)).get("product")));
                dataMap.put("butch", (double) (template.get(String.valueOf(month - 1)).get("butch") == 0 ? template.get(String.valueOf(month)).get("butch") : (template.get(String.valueOf(month)).get("butch") - template.get(String.valueOf(month - 1)).get("butch")) / template.get(String.valueOf(month - 1)).get("butch")));
                dataMap.put("batch", (double) (template.get(String.valueOf(month - 1)).get("batch") == 0 ? template.get(String.valueOf(month)).get("batch") : (template.get(String.valueOf(month)).get("batch") - template.get(String.valueOf(month - 1)).get("batch")) / template.get(String.valueOf(month - 1)).get("batch")));
                dataMap.put("farm", (double) (template.get(String.valueOf(month - 1)).get("farm") == 0 ? template.get(String.valueOf(month)).get("farm") : (template.get(String.valueOf(month)).get("farm") - template.get(String.valueOf(month - 1)).get("farm")) / template.get(String.valueOf(month - 1)).get("farm")));
                dataMap.put("shop", (double) (template.get(String.valueOf(month - 1)).get("shop") == 0 ? template.get(String.valueOf(month)).get("shop") : (template.get(String.valueOf(month)).get("shop") - template.get(String.valueOf(month - 1)).get("shop")) / template.get(String.valueOf(month - 1)).get("shop")));
                dataMap.put("unit", (double) (template.get(String.valueOf(month - 1)).get("unit") == 0 ? template.get(String.valueOf(month)).get("unit") : (template.get(String.valueOf(month)).get("unit") - template.get(String.valueOf(month - 1)).get("unit")) / template.get(String.valueOf(month - 1)).get("unit")));
            } else {
                OperationsQuery operationsQuery = OperationsQuery.builder()
                        .year(String.valueOf(Integer.parseInt(query.getYear()) - 1)).build();
                List<Map<String, Integer>> beforeYearMonthData = getMarketOperationOriginData(operationsQuery);
                for (Map<String, Integer> before : beforeYearMonthData) {
                    if (before.get("time") == 1) {
                        dataMap.put("plant", (double) (before.get("plant") == 0 ? template.get(String.valueOf(month)).get("plant") : (template.get(String.valueOf(month)).get("plant") - before.get("plant")) / before.get("plant")));
                        dataMap.put("animal", (double) (before.get("animal") == 0 ? template.get(String.valueOf(month)).get("animal") : (template.get(String.valueOf(month)).get("animal") - before.get("animal")) / before.get("animal")));
                        dataMap.put("product", (double) (before.get("product") == 0 ? template.get(String.valueOf(month)).get("product") : (template.get(String.valueOf(month)).get("product") - before.get("product")) / before.get("product")));
                        dataMap.put("butch", (double) (before.get("butch") == 0 ? template.get(String.valueOf(month)).get("butch") : (template.get(String.valueOf(month)).get("butch") - before.get("butch")) / before.get("butch")));
                        dataMap.put("batch", (double) (before.get("batch") == 0 ? template.get(String.valueOf(month)).get("batch") : (template.get(String.valueOf(month)).get("batch") - before.get("batch")) / before.get("batch")));
                        dataMap.put("farm", (double) (before.get("farm") == 0 ? template.get(String.valueOf(month)).get("farm") : (template.get(String.valueOf(month)).get("farm") - before.get("farm")) / before.get("farm")));
                        dataMap.put("shop", (double) (before.get("shop") == 0 ? template.get(String.valueOf(month)).get("shop") : (template.get(String.valueOf(month)).get("shop") - before.get("shop")) / before.get("shop")));
                        dataMap.put("unit", (double) (before.get("unit") == 0 ? template.get(String.valueOf(month)).get("unit") : (template.get(String.valueOf(month)).get("unit") - before.get("unit")) / before.get("unit")));
                    }
                }
            }
            data.add(dataMap);
        }
        return data;
    }

    private void initCountMap() {
        for (int month = 1; month <= 12; month++) {
            plantBusiness.put(String.valueOf(month), 0);
            animalBusiness.put(String.valueOf(month), 0);
            productBusiness.put(String.valueOf(month), 0);
            butchBusiness.put(String.valueOf(month), 0);
            batchBusiness.put(String.valueOf(month), 0);
            farmBusiness.put(String.valueOf(month), 0);
            shopBusiness.put(String.valueOf(month), 0);
            unitBusiness.put(String.valueOf(month), 0);
        }
    }

    private void countBusiness(Enterprise enterprise, String time) {
        String index = time.split("-")[1];
        index = String.valueOf(Integer.parseInt(index));
        if (enterprise.getIlk() == 0) {
            unitBusiness.put(index, unitBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 1) {
            plantBusiness.put(index, plantBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 2) {
            animalBusiness.put(index, animalBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 3) {
            productBusiness.put(index, productBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 4) {
            butchBusiness.put(index, butchBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 5) {
            batchBusiness.put(index, batchBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 6) {
            farmBusiness.put(index, farmBusiness.get(index) + 1);
        } else if (enterprise.getIlk() == 7) {
            shopBusiness.put(index, shopBusiness.get(index) + 1);
        }
    }

    private List<Map<String, Integer>> dataCollect() {
        List<Map<String, Integer>> data = new ArrayList<>();
        Set<String> keySet = plantBusiness.keySet();
        keySet.forEach(key -> {
            Map<String, Integer> countData = new HashMap<>();
            countData.put("time", Integer.valueOf(key));
            countData.put("plant", plantBusiness.get(key));
            countData.put("animal", animalBusiness.get(key));
            countData.put("product", productBusiness.get(key));
            countData.put("butch", butchBusiness.get(key));
            countData.put("batch", batchBusiness.get(key));
            countData.put("farm", farmBusiness.get(key));
            countData.put("shop", shopBusiness.get(key));
            countData.put("unit", unitBusiness.get(key));
            data.add(countData);
        });
        return data;
    }

}
