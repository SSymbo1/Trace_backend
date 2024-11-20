package edu.hrbu.trace_backend_business.entity.dto.analysis;

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
public class ProvinceData {

    private Integer total;
    private Double enter;
    private Double outer;
    private List<ProvinceValue> values;
    private List<Province> provinces;

}
