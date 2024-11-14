package edu.hrbu.trace_backend_job.entity.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class EnterpriseTypeCount {
    private String name;
    private Integer normal;
    private Integer focus;
}
