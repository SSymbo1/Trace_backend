package edu.hrbu.trace_backend.entity.dto.analysis;

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
public class StructQuery {
    @NotNull(message = "统计日期不能为空")
    private String date;
    @NotNull(message = "统计类型不能为空")
    private String type;
}
