package edu.hrbu.trace_backend_job.entity.dto.monitor;

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
public class InfoQuery {
    private String before;
    private String now;
    @NotNull(message = "必须提供分类")
    private String className;
}
