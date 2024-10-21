package edu.hrbu.trace_backend.entity.dto.analysis.base;

import edu.hrbu.trace_backend.entity.dto.analysis.ProvinceData;
import edu.hrbu.trace_backend.entity.po.ApproachClassCount;
import edu.hrbu.trace_backend.entity.po.ApproachCount;
import edu.hrbu.trace_backend.entity.po.EntranceClassCount;
import edu.hrbu.trace_backend.entity.po.EntranceCount;
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
    private Integer entranceSum;
    private Integer approachSum;
    private Double entranceYOY;
    private Double entranceQOQ;
    private Double approachYOY;
    private Double approachQOQ;
    private Integer entranceFresh;
    private Integer entranceProduct;
    private Integer entranceDrink;
    private Integer entranceFood;
    private Integer approachFresh;
    private Integer approachProduct;
    private Integer approachDrink;
    private Integer approachFood;
    private ProvinceData entranceProvinceList;
    private List<EntranceClassCount> entranceClassList;
    private List<ApproachClassCount> approachClassList;
    private List<EntranceCount> entranceTotalList;
    private List<ApproachCount> approachTotalList;
}
