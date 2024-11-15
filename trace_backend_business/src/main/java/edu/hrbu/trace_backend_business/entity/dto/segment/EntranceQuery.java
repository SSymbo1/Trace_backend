package edu.hrbu.trace_backend_business.entity.dto.segment;

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
public class EntranceQuery {
    @NotNull(message = "当前页码不能为空")
    private Integer currentPage;
    @NotNull(message = "分页大小不能为空")
    private Integer pageSize;
    private String name;
    private String batch;
    private String trace;
    private String buyerType;
}
