package edu.hrbu.trace_backend_business.entity.dto.analysis.base;

import edu.hrbu.trace_backend_business.entity.dto.analysis.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer entranceSum; // 出场总量
    private Integer approachSum; // 进场总量
    private Double entranceYOY; // 出场同比
    private Double entranceQOQ; // 出场环比
    private Double approachYOY; // 进场同比
    private Double approachQOQ; // 进场环比
    private Integer entranceFresh; // 出场生鲜
    private Integer entranceProduct; // 出场加工
    private Integer entranceDrink; // 出场饮料
    private Integer entranceFood; // 出场食品
    private Integer approachFresh; // 进场生鲜
    private Integer approachProduct; // 进场加工
    private Integer approachDrink; // 进场饮料
    private Integer approachFood; // 进场食品
    private ProvinceData provinceDataList; // 产地数据
    private List<EntranceClassCount> entranceClassList; // 出场分类
    private List<ApproachClassCount> approachClassList; // 进场分类
    private List<EntranceCount> entranceTotalList; // 出场总量
    private List<ApproachCount> approachTotalList; // 进场总量
    private List<Rank> rankList; // 销售数据分析
}
