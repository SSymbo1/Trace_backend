package edu.hrbu.trace_backend.entity.dto.analysis.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Query {
    @NotNull(message = "统计日期类型不能为空")
    private String type;
    @NotEmpty(message = "开始日期不能为空")
    private String before;
    @NotEmpty(message = "结束日期不能为空")
    private String now;
}
