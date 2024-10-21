package edu.hrbu.trace_backend.entity.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

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
