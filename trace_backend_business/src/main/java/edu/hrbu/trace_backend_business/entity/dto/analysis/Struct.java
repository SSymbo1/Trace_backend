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
public class Struct {

    private Integer total;
    private List<StructData> product;
    private List<StructData> plant;
    private List<StructData> animal;
    private List<StructData> market;
    private List<StructData> batch;
    private List<StructData> butch;
    private List<StructData> farm;

}
