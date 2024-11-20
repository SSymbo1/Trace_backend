package edu.hrbu.trace_backend_business.entity.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class OperationsQuery {
    @NotNull(message = "必须提供查询类型")
    private String type;
    private String tag;
    private String startYear;
    private String endYear;
    private String year;
}
