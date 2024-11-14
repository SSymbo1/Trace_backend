package edu.hrbu.trace_backend_job.entity.dto.system;

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
public class RoleQuery {
    @NotNull(message = "当前页码不能为空")
    private Integer currentPage;
    @NotNull(message = "分页大小不能为空")
    private Integer pageSize;
    private String name;
    private Integer ban;
    private Integer del;
}
