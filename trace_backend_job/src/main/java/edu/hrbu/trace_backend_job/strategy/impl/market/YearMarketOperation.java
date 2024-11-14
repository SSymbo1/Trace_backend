package edu.hrbu.trace_backend_job.strategy.impl.market;

import edu.hrbu.trace_backend_job.entity.dto.analysis.OperationsQuery;
import edu.hrbu.trace_backend_job.entity.po.*;
import edu.hrbu.trace_backend_job.mapper.*;
import edu.hrbu.trace_backend_job.strategy.MarketOperationStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;

@Component("year")
public class YearMarketOperation implements MarketOperationStrategy {
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
        initCountMap(query.getStartYear(), query.getEndYear());
        List<Approach> approaches = approachMapper.selectApproachInfoByYearBetween(query.getStartYear(), query.getEndYear());
        List<Entrance> entrances = entranceMapper.selectEntranceInfoByYearBetween(query.getStartYear(), query.getEndYear());
        List<ProductRecord> processed = productRecordMapper.selectProcessedInfoByYearBetween(query.getStartYear(), query.getEndYear());
        List<ProductRecord> insert = productRecordMapper.selectInsertInfoByYearBetween(query.getStartYear(), query.getEndYear());
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
        return getMarketOperationYOYData(query, origin);
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
        String start = query.getEndYear().split("-")[0];
        String end = query.getStartYear().split("-")[0];
        int startYear = Integer.parseInt(start);
        int endYear = Integer.parseInt(end);
        for (int year = startYear; year >= endYear; year--) {
            Map<String, Double> dataMap = new HashMap<>();
            dataMap.put("time", (double) year);
            if (year != endYear) {
                dataMap.put("plant", (double) (template.get(String.valueOf(year - 1)).get("plant") == 0 ? template.get(String.valueOf(year)).get("plant") : (template.get(String.valueOf(year)).get("plant") - template.get(String.valueOf(year - 1)).get("plant")) / template.get(String.valueOf(year - 1)).get("plant")));
                dataMap.put("animal", (double) (template.get(String.valueOf(year - 1)).get("animal") == 0 ? template.get(String.valueOf(year)).get("animal") : (template.get(String.valueOf(year)).get("animal") - template.get(String.valueOf(year - 1)).get("animal")) / template.get(String.valueOf(year - 1)).get("animal")));
                dataMap.put("product", (double) (template.get(String.valueOf(year - 1)).get("product") == 0 ? template.get(String.valueOf(year)).get("product") : (template.get(String.valueOf(year)).get("product") - template.get(String.valueOf(year - 1)).get("product")) / template.get(String.valueOf(year - 1)).get("product")));
                dataMap.put("butch", (double) (template.get(String.valueOf(year - 1)).get("butch") == 0 ? template.get(String.valueOf(year)).get("butch") : (template.get(String.valueOf(year)).get("butch") - template.get(String.valueOf(year - 1)).get("butch")) / template.get(String.valueOf(year - 1)).get("butch")));
                dataMap.put("batch", (double) (template.get(String.valueOf(year - 1)).get("batch") == 0 ? template.get(String.valueOf(year)).get("batch") : (template.get(String.valueOf(year)).get("batch") - template.get(String.valueOf(year - 1)).get("batch")) / template.get(String.valueOf(year - 1)).get("batch")));
                dataMap.put("farm", (double) (template.get(String.valueOf(year - 1)).get("farm") == 0 ? template.get(String.valueOf(year)).get("farm") : (template.get(String.valueOf(year)).get("farm") - template.get(String.valueOf(year - 1)).get("farm")) / template.get(String.valueOf(year - 1)).get("farm")));
                dataMap.put("shop", (double) (template.get(String.valueOf(year - 1)).get("shop") == 0 ? template.get(String.valueOf(year)).get("shop") : (template.get(String.valueOf(year)).get("shop") - template.get(String.valueOf(year - 1)).get("shop")) / template.get(String.valueOf(year - 1)).get("shop")));
                dataMap.put("unit", (double) (template.get(String.valueOf(year - 1)).get("unit") == 0 ? template.get(String.valueOf(year)).get("unit") : (template.get(String.valueOf(year)).get("unit") - template.get(String.valueOf(year - 1)).get("unit")) / template.get(String.valueOf(year - 1)).get("unit")));
            } else {
                OperationsQuery operationsQuery = OperationsQuery.builder()
                        .startYear((year - 1) + "-1-1")
                        .endYear((year - 1) + "-12-31").build();
                List<Map<String, Integer>> beforeYearData = getMarketOperationOriginData(operationsQuery);
                dataMap.put("plant", (double) (beforeYearData.get(beforeYearData.size() - 1).get("plant") == 0 ? template.get(String.valueOf(year)).get("plant") : (template.get(String.valueOf(year)).get("plant") - beforeYearData.get(beforeYearData.size() - 1).get("plant")) / beforeYearData.get(beforeYearData.size() - 1).get("plant")));
                dataMap.put("animal", (double) (beforeYearData.get(beforeYearData.size() - 1).get("animal") == 0 ? template.get(String.valueOf(year)).get("animal") : (template.get(String.valueOf(year)).get("animal") - beforeYearData.get(beforeYearData.size() - 1).get("animal")) / beforeYearData.get(beforeYearData.size() - 1).get("animal")));
                dataMap.put("product", (double) (beforeYearData.get(beforeYearData.size() - 1).get("product") == 0 ? template.get(String.valueOf(year)).get("product") : (template.get(String.valueOf(year)).get("product") - beforeYearData.get(beforeYearData.size() - 1).get("product")) / beforeYearData.get(beforeYearData.size() - 1).get("product")));
                dataMap.put("butch", (double) (beforeYearData.get(beforeYearData.size() - 1).get("butch") == 0 ? template.get(String.valueOf(year)).get("butch") : (template.get(String.valueOf(year)).get("butch") - beforeYearData.get(beforeYearData.size() - 1).get("butch")) / beforeYearData.get(beforeYearData.size() - 1).get("butch")));
                dataMap.put("batch", (double) (beforeYearData.get(beforeYearData.size() - 1).get("batch") == 0 ? template.get(String.valueOf(year)).get("batch") : (template.get(String.valueOf(year)).get("batch") - beforeYearData.get(beforeYearData.size() - 1).get("batch")) / beforeYearData.get(beforeYearData.size() - 1).get("batch")));
                dataMap.put("farm", (double) (beforeYearData.get(beforeYearData.size() - 1).get("farm") == 0 ? template.get(String.valueOf(year)).get("farm") : (template.get(String.valueOf(year)).get("farm") - beforeYearData.get(beforeYearData.size() - 1).get("farm")) / beforeYearData.get(beforeYearData.size() - 1).get("farm")));
                dataMap.put("shop", (double) (beforeYearData.get(beforeYearData.size() - 1).get("shop") == 0 ? template.get(String.valueOf(year)).get("shop") : (template.get(String.valueOf(year)).get("shop") - beforeYearData.get(beforeYearData.size() - 1).get("shop")) / beforeYearData.get(beforeYearData.size() - 1).get("shop")));
                dataMap.put("unit", (double) (beforeYearData.get(beforeYearData.size() - 1).get("unit") == 0 ? template.get(String.valueOf(year)).get("unit") : (template.get(String.valueOf(year)).get("unit") - beforeYearData.get(beforeYearData.size() - 1).get("unit")) / beforeYearData.get(beforeYearData.size() - 1).get("unit")));
            }
            data.add(dataMap);
        }
        return data;
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

    private void initCountMap(String start, String end) {
        for (int year = Integer.parseInt(start.split("-")[0]); year <= Integer.parseInt(end.split("-")[0]); year++) {
            plantBusiness.put(String.valueOf(year), 0);
            animalBusiness.put(String.valueOf(year), 0);
            productBusiness.put(String.valueOf(year), 0);
            butchBusiness.put(String.valueOf(year), 0);
            batchBusiness.put(String.valueOf(year), 0);
            farmBusiness.put(String.valueOf(year), 0);
            shopBusiness.put(String.valueOf(year), 0);
            unitBusiness.put(String.valueOf(year), 0);
        }
    }

    private void countBusiness(Enterprise enterprise, String time) {
        String index = time.split("-")[0];
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

}
